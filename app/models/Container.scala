package models

import java.util.{Date}
//import java.lang.Double

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

case class Container(
  id: Pk[Long] = NotAssigned,
  name: String,
  temperatureExpected: Option[Double], 
  temperatureRange: Option[Double],
  readFrequency: Option[Long],
  monitorID: Option[Long] 
)

case class ContainerReading(
  id: Pk[Long] = NotAssigned,
  readTemperature: Double,
  readStatus: String,
  readTime: Date
)

case class ContainerReadData(
  readID: Long,
  readTemperature: Double,
  readStatus: String,
  readTime: Date,
  readNote: Option[String]
)

case class ContainerListView(
  index: Long, 
  name: String,
  lastReadTemperature: Option[Double], 
  lastReadStatus: Option[String],
  lastReadTime: Option[Date]
)

case class ContainerEditData(
  name: String,
  temperatureExpected: Option[Double],
  temperatureRange: Option[Double],
  readFrequency: Option[Long],
  monitorID: Option[Long]
)

case class ContainerCreateData(
  name: String,
  index: Long, 
  temperatureExpected: Option[Double],
  temperatureRange: Option[Double], 
  readFrequency: Option[Long],
  monitorID: Option[Long]
)

case class ContainerNote(note: String)

case class ContainerReadOverdueData(
  listNumber: Long,
  index: Long,
  name: String,
  readFrequency: Option[Long],
  readTime: Option[Date]
)

object Container
{
  implicit def javaFloatToDouble(f: java.lang.Float) = f.doubleValue

  // -- Parsers 
/**
   * Parse a Container from a ResultSet
   */
  val simple = {
    get[Pk[Long]]("container.id") ~
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

  val listView = {
    get[Long]("container_list.container_index") ~ 
    get[String]("container.name") ~
    get[Option[Double]]("read_temperature") ~
    get[Option[String]]("read_status") ~
    get[Option[Date]]("read_time") map {
      case (index~name~lastReadTemperature~lastReadStatus~
          lastReadTime) =>
        ContainerListView(index, name, lastReadTemperature, lastReadStatus,
          lastReadTime)
    }
  }

  val reading = {
    get[Long]("container_readings.reading_id") ~
    get[Double]("container_readings.read_temperature") ~
    get[String]("container_readings.read_status") ~
    get[Date]("container_readings.read_time") ~
    get[Option[String]]("container_readings.read_note") map {
      case (id~temp~status~date~note) => 
        ContainerReadData(id, temp, status, date, note)
    }
  }

  val readingOverdue = {
    get[Long]("container_list.list_number") ~
    get[Long]("container_list.container_index") ~
    get[String]("container.name") ~
    get[Option[Long]]("container.read_frequency") ~
    get[Option[Date]]("container_readings.read_time") map {
      case (list_number~index~name~read_frequency~read_time) =>
        ContainerReadOverdueData(list_number, index, name, read_frequency, 
          read_time)
    }
  }

  /**
   * Parse a (Container,Monitor) from a ResultSet
   */
  val withMonitor = Container.simple ~ (Monitor.simple ?) map {
    case container~monitor => (container, monitor)
  }
  
  def toEdit(container: Container) = ContainerEditData(container.name, 
    container.temperatureExpected, container.temperatureRange, 
    container.readFrequency, container.monitorID)

  def toEditNote(reading: ContainerReadData) = ContainerNote(reading.readNote.getOrElse(""))

  // -- Queries
  
  /**
   * Retrieve a container by the given id.
   */
  def findByID(id: Long): Option[Container] = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM container WHERE id = {id}").on(
        'id -> id
      ).as(Container.simple.singleOpt)
    }
  }

  /**
    * Get the index of this container associating it to its user's container list.
    */
  def getContainerIndex(container: Container): Long = {
    DB.withConnection { implicit connection =>
      SQL(
        """
        SELECT fl.container_index 
        FROM container_list as fl

        JOIN container AS f ON f.id = fl.container_id

        WHERE f.id = {id};
        """
      ).on(
        'id -> container.id
      ).as(scalar[Long].single)
    }
  }

  /*
   * Get container associated to user with email 'email' that has index in that
   * user's container list of 'id'
   */
  def findUnderAdminByIndex(index: Long, email: String): Option[Container] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
        SELECT container.* 
        FROM container

        JOIN container_list ON container_list.container_id = container.id

        JOIN user ON user.container_list_number = container_list.list_number

        WHERE user.email = {email}
        AND container_list.container_index = {index}
        """
      ).on(
        'email -> email,
        'index -> index
      ).as(Container.simple.singleOpt)
    }
  }
  
  /*
   * Get all containers belonging to admin with email 'email', and the most 
   * recent reading from each container.
   */
  def findUnderAdmin(
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
               fr.read_temperature,
               fr.read_status,
               fr.read_time
        FROM container_list AS fl
 
        JOIN container AS f 
        ON f.id = fl.container_id

        JOIN user AS u
        ON u.container_list_number = fl.list_number

        LEFT JOIN (
          SELECT frb.* 
          FROM container_readings AS frb

          JOIN (
            SELECT container_id,
                   max(read_time) as max_time
            FROM container_readings
            
            GROUP BY container_id
          ) AS fra
          on fra.container_id = frb.container_id 
          AND fra.max_time = frb.read_time

        ) AS fr 
        ON fr.container_id = fl.container_id

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
   * @param id The container id
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
   * @param id ID of the container to delete.
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


  def adminsOf(container: Container): Seq[User] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          SELECT user.* 
          FROM user u

          JOIN container_list l 
          ON l.list_number = u.container_list_number

          where l.container_id = {containerID}
        """
      ).on(
        'containerID -> container.id.get
      ).as(User.simple *)
    }
  }

  def isAdmin(container_id: Long, user: String): Boolean = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          select count(user.email) = 1 from user
          join container_list on 
            container_list.list_number = user.container_list_number
          where container_list.container_id = {id}
          and user.email = {email}
        """
      ).on(
        'id -> container_id,
        'email -> user
      ).as(scalar[Boolean].single)
    }
  }

  /*
   * convert the container with index into a Json object for a client
   * data includes:
   *   - time until next update
   *   - temperature expected
   *   - temperature range
   *   - monitor data:
   *       i.  Product ID
   *       ii. Vendor ID
   *   - where to upload reading
   */
  def toJson(index: Long, user: String): Option[JsObject] = {
    // time until next upload = (last upload)+(read freq)-(now)
    // if the time to next upload is negative, set as 0
    Container.findUnderAdminByIndex(index, user).map { container =>
      val uploadURL: String = "/containers/"+index.toString+("/uploadpkg.json")

      val now = new Date().getTime()
      val lastRead = Container.getLastReadTime(container)
      val freq: Long = container.readFrequency.getOrElse{900}

      val temp: String = container.temperatureExpected.getOrElse{"-80.0"}.toString
      val tempRange: String = container.temperatureRange.getOrElse{"15.0"}.toString

      val monitorID: Long = container.monitorID.getOrElse{1}
      val monitor: JsObject = Monitor.toJson(monitorID).getOrElse{Json.obj()}

      Json.obj(
        "specifications" -> Json.obj(
          "nextUpdateIn"->roundTime(((lastRead+(freq*1000)-now))/1000).toString(),
          "expectedTemperature" -> temp,
          "temperatureRange" -> tempRange,
          "monitor" -> monitor,
          "uploadURL" -> uploadURL
        )
      )
    }
  }
  /*
   * Update a container with given index under given user with data from JsVal
   */
  def addReading(
    index: Long, 
    user: String, 
    temperature: Double,
    status:String, 
    time: Date
  ) = {
    val container = findUnderAdminByIndex(index, user).get

    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into container_readings (
            container_id,
            read_temperature,
            read_status,
            read_time
          )
          values (
            {id},
            {temperature},
            {status},
            {time} 
          );
        """
      ).on(
        'id -> container.id,
        'temperature -> temperature,
        'status -> status,
        'time -> time
      ).executeUpdate()
    }
  }

  def addNote(
    containerID: anorm.Pk[Long],
    readID: Long,
    containerNote: String
  ) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          UPDATE container_readings as c
            SET read_note = IF(
                               read_note IS NULL, 
                               {note}, 
                               CONCAT(c.read_note, "; ", {note})
                              ),
                read_time = read_time

            WHERE container_id = {id}
            AND reading_id = {read_id};
        """
      ).on(
        'note -> containerNote,
        'id -> containerID,
        'read_id -> readID
      ).executeUpdate()
    }
  }

  /*
   * Get all readings for the given container under the given user
   */
  def getReadings(
    index: Long, 
    user: String, 
    page: Int, 
    pageSize: Int = 10,
    orderBy: Int = 3
  ): Page[ContainerReadData] = {
    val container = findUnderAdminByIndex(index, user).get
    val offset = pageSize * page

    DB.withConnection { implicit connection =>
      val readings = SQL(
        """
          SELECT fr.reading_id,
                 fr.read_temperature, 
                 fr.read_status,
                 fr.read_time,
                 fr.read_note
          FROM container_readings AS fr
          
          WHERE fr.container_id = {containerID}
          
          ORDER BY read_time DESC
          LIMIT {pageSize} OFFSET {offset};
        """
      ).on(
        'containerID -> container.id,
        'orderBy -> orderBy,
        'pageSize -> pageSize,
        'offset -> offset
      ).as(Container.reading *)

      val totalReadings = SQL(
        """
          SELECT count(*) 
          FROM container_readings AS fr
          
          WHERE fr.container_id = {id};
        """
      ).on(
        'id -> container.id
      ).as(scalar[Long].single) 

      Page(readings, page, offset, totalReadings)
    }
  }

  def getLastReadTime(container: Container): Long = {
    DB.withConnection { implicit connection =>
      val reading = SQL(
        """
          select fr.read_temperature,
                 fr.read_status,
                 fr.read_time,
                 fr.reading_id,
                 fr.read_note
          from container_readings as fr
           
          where fr.container_id = {containerID}

          order by fr.read_time desc
          limit 1
        """
      ).on(
        'containerID -> container.id
      ).as(Container.reading.singleOpt) 

      reading.map { read =>
        read.readTime.getTime().toLong
      }.getOrElse{ 0 }
    }
  }

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
        'containerID -> container.id,
        'readingID -> readID
      ).as(Container.reading.singleOpt)
    }
  }

  /*
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
}

