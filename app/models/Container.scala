package models

import org.joda.time.DateTime
import org.joda.time.format.{ DateTimeFormat, DateTimeFormatter }

import play.api.db._
import play.api.Play.current

import play.api.libs.json._

import com.typesafe.plugin._

import anorm._
import anorm.SqlParser._

import controllers._
import utils.timeUtils._

import utils._

import Monitor._
import Page._
import User._

/**
 * Case class for the container as it is represented in MySQL
 */
case class Container(
  id: Option[Long] = None,
  name: String,
  temperatureExpected: Option[Double],
  temperatureRange: Option[Double],
  readFrequency: Option[Long],
  monitorID: Option[Long]
)

/**
 * Case class for a single reading for a container
 */
case class ContainerReading(
  id: Option[Long] = None,
  readTemperature: Double,
  readStatus: String,
  readTime: DateTime
)

/**
 * Similar to ContainerReading but with note for user viewing.
 */
case class ContainerReadData(
  readID: Long,
  containerId: Long,
  readTemperature: Double,
  readStatus: String,
  readTime: DateTime,
  readNote: Option[String]
)

/**
 * Case class for a container as it appears on the container list page
 */
case class ContainerListView(
  index: Long,
  name: String,
  lastReadTemperature: Option[Double],
  lastReadStatus: Option[String],
  lastReadTime: Option[DateTime]
)

/**
 * Case class for container as it appears on the edit container page
 */
case class ContainerEditData(
  name: String,
  temperatureExpected: Option[Double],
  temperatureRange: Option[Double],
  readFrequency: Option[Long],
  monitorID: Option[Long]
)

/**
 * Case class for container as it appears on the create container page
 */
case class ContainerCreateData(
  name: String,
  index: Long,
  temperatureExpected: Option[Double],
  temperatureRange: Option[Double],
  readFrequency: Option[Long],
  monitorID: Option[Long]
)

/**
 * Case class wasn't really necessary, mostly added it here for consistency
 */
case class ContainerNote(note: String)

/**
 * Case class for determining if a container has a reading that is overdue
 */
case class ContainerReadOverdueData(
  listNumber: Long,
  index: Long,
  name: String,
  readFrequency: Option[Long],
  readTime: Option[DateTime]
)

object Container
{
  implicit def javaFloatToDouble(f: java.lang.Float) = f.doubleValue

  val dateFormatGeneration: DateTimeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmssSS")

  val timeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

  implicit def rowToDateTime: Column[DateTime] = Column.nonNull { (value, meta) =>
    val MetaDataItem(qualified, nullable, clazz) = meta
    value match {
      case ts: java.sql.Timestamp => Right(new DateTime(ts.getTime))
      case d: java.sql.Date => Right(new DateTime(d.getTime))
      case str: java.lang.String => Right(dateFormatGeneration.parseDateTime(str))
      case _ => Left(TypeDoesNotMatch("Cannot convert " + value + ":" + value.asInstanceOf[AnyRef].getClass) )
    }
  }

  // -- Parsers
  /**
   * Parse a Container from a ResultSet
   */
  val simple = {
    get[Option[Long]]("container.id") ~
    get[String]("container.name") ~
    get[Option[Double]]("container.temperature_expected") ~
    get[Option[Double]]("container.temperature_range") ~
    get[Option[Long]]("container.read_frequency") ~
    get[Option[Long]]("container.monitor_id") map {
      case (id~name~temperatureExpected~temperatureRange~
          readFrequency~monitorID) =>
        Container(id, name, temperatureExpected, temperatureRange,
          readFrequency, monitorID)
    }
  }

  /**
   * Parse a container from a ResultSet, including its last reading
   */
  val listView = {
    get[Long]("container_list.container_index") ~
    get[String]("container.name") ~
    get[Option[Double]]("container.last_read_temperature") ~
    get[Option[String]]("container.last_read_status") ~
    get[Option[DateTime]]("container.last_read_time") map {
      case (index~name~lastReadTemperature~lastReadStatus~lastReadTime) =>
        ContainerListView(index, name, lastReadTemperature, lastReadStatus,
          lastReadTime)
    }
  }

  /**
   * Parse a reading from a ResultSet
   */
  val reading = {
    get[Long]("container_readings.reading_id") ~
    get[Long]("container_readings.container_id") ~
    get[Double]("container_readings.read_temperature") ~
    get[String]("container_readings.read_status") ~
    get[DateTime]("container_readings.read_time") ~
    get[Option[String]]("container_readings.read_note") map {
      case (id~containerId~temp~status~date~note) =>
        ContainerReadData(id, containerId, temp, status, date, note)
    }
  }

  /**
   * Parse an overdue reading from a ResultSet
   */
  val readingOverdue = {
    get[Long]("container_list.list_number") ~
    get[Long]("container_list.container_index") ~
    get[String]("container.name") ~
    get[Option[Long]]("container.read_frequency") ~
    get[Option[DateTime]]("container_readings.read_time") map {
      case (list_number~index~name~read_frequency~read_time) =>
        ContainerReadOverdueData(list_number, index, name, read_frequency,
          read_time)
    }
  }

  /**
   * Parse a (Container, Monitor) from a ResultSet
   */
  val withMonitor = Container.simple ~ (Monitor.simple ?) map {
    case container~monitor => (container, monitor)
  }

  /**
   * Since edit data is a subset of a full container data, convert a container
   * to its edit data by removing a few parameters
   */
  def toEdit(container: Container) = ContainerEditData(container.name,
    container.temperatureExpected, container.temperatureRange,
    container.readFrequency, container.monitorID)

  /**
   * Reduce a container to just its note, if it has one.
   */
  def toEditNote(reading: ContainerReadData) = ContainerNote(reading.readNote.getOrElse(""))

  // -- Queries

  /**
   * Retrieve a container by the given id.
   *
   * @param id ID of the container
   */
  def getByID(id: Long): Option[Container] = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM container WHERE id = {id}").on(
        'id -> id
      ).as(Container.simple.singleOpt)
    }
  }

  /**
   * Get the index of this container associating it to its user's container
   * list.
   *
   * @param container Container for which index is desired
   */
  def getContainerIndex(container: Container): Long = {
    DB.withConnection { implicit connection =>
      SQL"""
        SELECT fl.container_index
        FROM container_list as fl

        JOIN container AS f ON f.id = fl.container_id

        WHERE f.id = ${container.id.get};
        """.as(scalar[Long].single)
    }
  }

  /**
   * Get container associated to user with email 'email' that has index in that
   * user's container list of 'id'
   *
   * @param index Index of the desired container
   * @param email Email of the user searching for container with given index
   */
  def getOwnedByAdminByIndex(index: Long, email: String): Option[Container] = {
    DB.withConnection { implicit connection =>
      SQL"""
        SELECT container.*
        FROM container

        JOIN container_list ON container_list.container_id = container.id

        JOIN user ON user.container_list_number = container_list.list_number

        WHERE user.email = $email
        AND container_list.container_index = $index
        """.as(Container.simple.singleOpt)
    }
  }

  /**
   * Get all containers belonging to admin with email 'email', and the most
   * recent reading from each container.
   *
   * @param email Email of user we want containers from
   * @param page Page number of containers
   * @param pageSize Number of containers to appear per page
   * @param orderBy How to order the containers
   * @param filter String container name must contain to appear in this list
   */
  def getOwnedByAdmin(
    email: String,
    page: Int = 0,
    pageSize: Int = 10,
    orderBy: Int,
    filter: String = "%"
  ): Page[ContainerListView] = {
    val offset = pageSize * page

    DB.withConnection { implicit connection =>
      val containers = SQL (
        """
        SELECT fl.container_index,
               f.name,
               f.last_read_temperature,
               f.last_read_status,
               f.last_read_time
        FROM container_list AS fl

        JOIN container AS f
        ON f.id = fl.container_id

        JOIN user AS u
        ON u.container_list_number = fl.list_number

        WHERE u.email = {email}
        AND f.name LIKE {filter}

        ORDER BY {orderBy}

        LIMIT {pageSize}
        OFFSET {offset};
        """
      ).on(
        'filter -> filter,
        'email -> email,
        'orderBy -> orderBy,
        'pageSize -> pageSize,
        'offset -> offset
      ).as(Container.listView *)

      val totalRows = SQL(
        """
          SELECT COUNT(*) FROM container
          JOIN container_list ON container.id = container_list.container_id
          JOIN user ON user.container_list_number = container_list.list_number
          WHERE container.name LIKE {filter}
          AND user.email = {email}
        """
      ).on(
        'filter -> filter,
        'email -> email
      ).as(scalar[Long].single)

      Page(containers, page, offset, totalRows)
    }
  }

  /**
   * Update a container.
   *
   * @param index The container index
   * @param email The email of the user performing the update
   * @param container The container values.
   */
  def update(index: Long, email: String, container: ContainerEditData) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          UPDATE container

          SET name = {name},
          temperature_expected = {temperatureExpected},
          temperature_range = {temperatureRange},
          read_frequency = {readFrequency},
          monitor_id = {monitorID}

          WHERE id = (
            SELECT container_list.container_id FROM container_list
            JOIN user ON user.container_list_number = container_list.list_number
            WHERE user.email = {email}
            AND container_list.container_index = {index}
          )
        """
      ).on(
        'index -> index,
        'email -> email,
        'name -> container.name,
        'temperatureExpected -> container.temperatureExpected,
        'temperatureRange -> container.temperatureRange,
        'readFrequency -> container.readFrequency,
        'monitorID -> container.monitorID
      ).executeUpdate()
    }
  }

  /**
   * Insert a new container.
   *
   * @param container The container values.
   * @param admins Users who have this container in their list
   */
  def insert(container: ContainerCreateData, admins: Seq[String]) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          INSERT INTO container (
            name,
            temperature_expected,
            temperature_range,
            read_frequency,
            monitor_id
          )
          VALUES (
            {name},
            {temperatureExpected},
            {temperatureRange},
            {readFrequency},
            {monitorID}
          )
        """
      ).on(
        'name -> container.name,
        'temperatureExpected -> container.temperatureExpected,
        'temperatureRange -> container.temperatureRange,
        'readFrequency -> container.readFrequency,
        'monitorID -> container.monitorID
      ).executeUpdate()

      admins.foreach { email =>
        SQL(
          """
            INSERT INTO container_list (list_number, container_index, container_id)
            SELECT user.container_list_number, {index}, last_insert_id()
            FROM user WHERE email = {email};
          """
        ).on(
          'index -> container.index,
          'email -> email
        ).executeUpdate()
      }
    }
  }

  /**
   * Delete a container.
   *
   * @param index Index of the container to delete.
   * @param email Email of the admin with container to delet
   */
  def delete(index: Long, email: String) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
           DELETE FROM container WHERE container.id = (
             SELECT container_list.container_id FROM container_list
             JOIN user ON user.container_list_number = container_list.list_number
             WHERE user.email = {email}
             AND container_list.container_index = {index}
           );
        """
      ).on(
        'index -> index,
        'email -> email
      ).executeUpdate()
    }
  }

  /**
   * Get all admins of given container
   *
   * @param container Retrieves owners of this container
   */
  def adminsOf(container: Container): Seq[User] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          SELECT * FROM user u

          JOIN container_list l
          ON l.list_number = u.container_list_number

          WHERE l.container_id = {containerID}
        """
      ).on(
        'containerID -> container.id.get
      ).as(User.simple *)
    }
  }

  /**
   * Determines if given user is an admin of container with given id
   *
   * @param container_id ID of container in question
   * @param user Email of user
   */
  def isAdmin(container_id: Long, user: String): Boolean = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          SELECT COUNT(user.email) = 1 FROM user

          JOIN container_list ON
            container_list.list_number = user.container_list_number

          WHERE container_list.container_id = {id}
          AND USER.EMAIL = {EMAIL};
        """
      ).on(
        'id -> container_id,
        'email -> user
      ).as(scalar[Boolean].single)
    }
  }

  /**
   * convert the container with index into a Json object for a client
   * data includes:
   *   - time until next update
   *   - temperature expected
   *   - temperature range
   *   - monitor data:
   *       i.  Product ID
   *       ii. Vendor ID
   *   - where to upload reading
   *
   * @param index Index of container to JSONify
   * @param user Email of user with given container index
   */
  def toJson(index: Long, user: String): Option[JsObject] = {
    // time until next upload = (last upload)+(read freq)-(now)
    // if the time to next upload is negative, set as 0
    Container.getOwnedByAdminByIndex(index, user).map { container =>
      val uploadURL: String = "/containers/"+index.toString+("/readings")

      val freq: Long = container.readFrequency.getOrElse{900}

      val monitorID: Long = container.monitorID.getOrElse{1}
      val monitor: JsObject = Monitor.toJson(monitorID).getOrElse{Json.obj()}

      Container.getLastReading(container).map { lastReading =>
        val now = DateTime.now.getMillis

        val lastReadTime = lastReading.readTime.getMillis
        val lastReadStatus = lastReading.readStatus

        Json.obj(
          "specifications" -> Json.obj(
            "nextUpdateIn"->roundTime(((lastReadTime+(freq*1000)-now))/1000).toString(),
            "lastReadStatus"->lastReadStatus,
            "monitor" -> monitor,
            "uploadURL" -> uploadURL
          )
        )
      }.getOrElse{
        Json.obj(
          "specifications" -> Json.obj(
            "nextUpdateIn"-> "0",
            "lastReadStatus"->"",
            "monitor" -> monitor,
            "uploadURL" -> uploadURL
          )
        )
      }
    }
  }

  /**
   * Update a container with given index under given user with data from JsVal
   *
   * @param index Index of container to add reading to
   * @param user Email of user with given container
   * @param temperature Temperature of uploaded reading
   * @param status Status of uploaded reading
   * @param time Time/date at which reading was uploaded
   */
  def addReading(
    index: Long,
    user: String,
    temperature: Double,
    status:String,
    time: DateTime
  ) = {
    val container = getOwnedByAdminByIndex(index, user).get

    DB.withConnection { implicit connection =>
      SQL"""
          insert into container_readings (
            container_id,
            read_temperature,
            read_status,
            read_time
          )
          values (
            ${container.id.get},
            $temperature,
            $status,
            ${timeFormatter.print(time)}
          );
        """.executeUpdate()

      SQL"""
          UPDATE container AS c

          SET last_read_temperature = $temperature,
              last_read_status = $status,
              last_read_time = ${timeFormatter.print(time)}

          WHERE c.id = ${container.id.get}
        """.executeUpdate()
    }
  }

  /**
   * Add note to container reading
   *
   * @param containerID ID of container with reading being added to
   * @param readID ID of reading being added to
   * @param containerNote note to add to reading
   */
  def addNote(
    containerID: Option[Long],
    readID: Long,
    containerNote: String
  ) = {
    DB.withConnection { implicit connection =>
      SQL"""
          UPDATE container_readings as c
            SET read_note = IF(read_note IS NULL,
                               $containerNote,
                               CONCAT(c.read_note, "; ", {note})),
                read_time = read_time

            WHERE container_id = ${containerID.get}
            AND reading_id = $readID;
        """.executeUpdate()
    }
  }

  /**
   * Get all readings for the given container under the given user
   *
   * @param index Index of container to get readings for
   * @param user Email of user with given container
   * @param page Page number of readings to return
   * @param pageSize Number of readings to return per page
   * @param orderBy what column to order readings by
   * @param filterErrors 1 = show only unresolved errors/warnings 0 = show all
   */
  def getReadings(
    index: Long,
    user: String,
    page: Int,
    pageSize: Int = 10,
    orderBy: Int = 3,
    filterErrors: Int = 0
  ): Page[ContainerReadData] = {
    val container = getOwnedByAdminByIndex(index, user).get
    val offset = pageSize * page

    DB.withConnection { implicit connection =>
      if (filterErrors == 1) {
        val readings = SQL"""
            SELECT fr.reading_id,
                   fr.read_temperature,
                   fr.read_status,
                   fr.read_time,
                   fr.read_note
            FROM container_readings AS fr

            WHERE fr.container_id = ${container.id.get}
            AND fr.read_note IS NULL
            AND (fr.read_status LIKE '%ERROR%'
            OR fr.read_status LIKE '%WARNING%')

            ORDER BY read_time DESC
            LIMIT $pageSize OFFSET $offset;
          """as(Container.reading *)

        val totalReadings = SQL"""
            SELECT count(*)
            FROM container_readings AS fr

            WHERE fr.container_id = ${container.id.get}
            AND fr.read_note IS NULL
            AND (fr.read_status LIKE '%ERROR%'
            OR fr.read_status LIKE '%WARNING%');
          """.as(scalar[Long].single)

        Page(readings, page, offset, totalReadings)
      } else {
        val readings = SQL"""
          SELECT cr.reading_id as id,
                 cr.container_id,
                 cr.read_temperature,
                 cr.read_status,
                 cr.read_time,
                 cr.reading_id,
                 cr.read_note
          FROM container_readings cr
          WHERE cr.container_id = ${container.id.get}

          ORDER BY cr.read_time DESC
          LIMIT $pageSize OFFSET $offset;
          """.as(Container.reading *)

        val totalReadings = SQL"""
          SELECT count(*)
          FROM container_readings AS fr

          WHERE fr.container_id = ${container.id.get};
        """.as(scalar[Long].single)

        Page(readings, page, offset, totalReadings)
      }
    }
  }

  /**
   * Get the time of the given container's last reading
   *
   * @param container Container of interest
   */
  def getLastReading(container: Container): Option[ContainerReadData] = {
    DB.withConnection { implicit connection =>
      SQL"""
          select fr.read_temperature,
                 fr.read_status,
                 fr.read_time,
                 fr.reading_id,
                 fr.read_note
          from container_readings as fr

          where fr.container_id = ${container.id.get}

          order by fr.read_time desc
          limit 1
        """.as(Container.reading.singleOpt)
    }
  }

  /**
   * Get specific reading from given container
   *
   * @param container Container with reading in question
   * @param readID ID of the reading to return
   */
  def getContainerReading(container: Container, readID: Long) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          select fr.reading_id,
                 fr.read_temperature,
                 fr.read_status,
                 fr.read_time,
                 fr.read_note
          from container_readings as fr

          where fr.container_id = {containerID}
          and fr.reading_id = {readingID}
        """
      ).on(
        'containerID -> s"$container.id",
        'readingID -> s"$readID"
      ).as(Container.reading.singleOpt)    }
  }

  /**
   * user1 <-> | container1 <-> last reading
   *             container2 <-> last reading
   *                       ...
   *             containerN <-> last reading
   *
   *       ...
   *
   * userN <-> | container1 <-> last reading
   *             container2 <-> last reading
   *                        ...
   *             containerN <-> last reading
   *
   * for each user get every container and its last temperature reading
   * if the last reading time is more than an hour overdue (if there is
   * a read frequency specified) then notify the admin (if the admin
   * has signed up to receive updates)
   *
   * container_list.list_number | container_list.index | container.name |
   *   container.read_frequency | container_reading.read_time (most recent)
   */

  def getLateReadings(): List[ContainerReadOverdueData] = {
    DB.withConnection { implicit connection =>
      val containers = SQL (
        """
        SELECT fl.list_number,
               fl.container_index,
               f.name,
               f.read_frequency,
               fr.read_time
        FROM container_list AS fl

        JOIN container AS f
        ON f.id = fl.container_id

        JOIN (
          SELECT frb.*
          FROM container_readings AS frb

          JOIN (
            SELECT container_id,
                   max(read_time) as max_time
            FROM container_readings

            GROUP BY container_id
          ) AS fra
          ON fra.container_id = frb.container_id
          AND fra.max_time = frb.read_time

        ) AS fr
        ON fr.container_id = fl.container_id

        WHERE (fr.read_time + INTERVAL f.read_frequency SECOND + INTERVAL 60 MINUTE) < NOW();
        """
      ).as(Container.readingOverdue *)

      return containers
    }
  }

  def getStatus(
    index: Long,
    email: String,
    temperature: Double
  ) = {
    Container.getOwnedByAdminByIndex(index, email).map { c =>
      temperature match {
        case x if x < (c.temperatureExpected.get - c.temperatureRange.get) =>
          "WARNING: temperature below expected range"
        case x if x > (c.temperatureExpected.get + c.temperatureRange.get) =>
          "WARNING: temperature above expected range"
        case _ =>
          "OK"
      }
    }.getOrElse("")
  }

  // Alarm for warnings
  def alarm(
    index: Long,
    email: String,
    temperature: Double,
    status: String,
    time: DateTime
  ) = {
    Container.getOwnedByAdminByIndex(index, email).map { c =>
      val title = "CBSR Freezer alarm"
      val message = s"""${c.name} #${index} recorded a temperature of $temperature and has recorded the following warning:

        $status

        recorded at ${timeFormatter.print(time)}."""
      Container.adminsOf(c).map { admin =>
        Email.sendEmail(admin.email, title, message)
      }
    }
  }

  // Alarm for errors
  def alarm(
    index: Long,
    email: String,
    error: String,
    time: DateTime
  ) = {
    Container.getOwnedByAdminByIndex(index, email).map { c =>
      val title = "CBSR Freezer alarm"
      val message = s"""${c.name} #$index has recorded the following error:

        $error

        recorded at ${timeFormatter.print(time)}."""
      Container.adminsOf(c).map { admin =>
        Email.sendEmail(admin.email, title, message)
      }
    }
  }

}

