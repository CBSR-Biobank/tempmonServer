package models

import java.sql.{ Connection, Statement, PreparedStatement }
import fixture.NameGenerator
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import scala.reflect.ClassTag
import scala.reflect._
import org.slf4j.LoggerFactory
import scalaz._
import scalaz.Scalaz._

/** Helper object for writing tests to persist model objects in the database.
  *
  * It uses JDBC to write to the database and does not use the applications model classes to write
  * to the database since that is the code that is to be tested. The idea is that this code is
  * independent of the application code.
  *
  * This class remembers the last model object it persisted to the database, in case the user
  * wishes to reference that object in his / her tests.
  */
object Factory {

  val log = LoggerFactory.getLogger(this.getClass)

  val nameGenerator = new NameGenerator(this.getClass)

  var modelObjects: Map[Class[_], _] = Map.empty

  val dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")

  def getLastInsertedId(statement: PreparedStatement): Long = {
    val keys = statement.getGeneratedKeys
    keys.next
    keys.getLong(1)
  }

  def createUser(implicit conn: Connection) = {
    val email = nameGenerator.nextEmail[User]
    val nameFirst = nameGenerator.next[User]
    val nameLast = nameGenerator.next[User]
    val password = nameGenerator.next[User]
    val birthday = dateFormatter.print(DateTime.now)
    val gender = "M"
    val country = nameGenerator.next[User]
    val containerListNumber = 0

    val sql = s"""
     |INSERT INTO user (
     |  email, name_first, name_last, password , birthday, gender, country, container_list_number)
     |VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )""".stripMargin

    val prep = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
    prep.setString(1, email)
    prep.setString(2, nameFirst)
    prep.setString(3, nameLast)
    prep.setString(4, password)
    prep.setString(5, birthday)
    prep.setString(6, gender)
    prep.setString(7, country)
    prep.setInt(8, containerListNumber)
    prep.executeUpdate
    val id = getLastInsertedId(prep)
    val user = User(Some(id), email, nameLast, password)

    modelObjects = modelObjects + (classOf[User] -> user)
    user
  }

  def createContainer(implicit conn: Connection) = {
    val name = nameGenerator.next[Container]
    val temperatureExpected = -80.0;
    val temperatureRange = 10.0
      val readFrequency = 10
        val monitorID = 0

    val sql = s"""
      |INSERT INTO container (
      |  name, temperature_expected, temperature_range, read_frequency, monitor_id)
      |VALUES ( ?, ?, ?, ?, ? )""".stripMargin

    val prep = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
    prep.setString(1, name)
    prep.setDouble(2, temperatureExpected)
    prep.setDouble(3, temperatureRange)
    prep.setLong(4,   readFrequency)
    prep.setLong(5,   monitorID)
    prep.executeUpdate
    val id = getLastInsertedId(prep)
    val container = Container(
      Some(id),
      name,
      Some(temperatureExpected),
      Some(temperatureRange),
      Some(readFrequency),
      Some(monitorID))

    modelObjects = modelObjects + (classOf[Container] -> container)
    container
  }

  def createMonitor(implicit conn: Connection) = {
    val manufacturer = nameGenerator.next[Monitor]
    val name = nameGenerator.next[Container]
    val productId = 100
    val vendorId = 100

    val sql = s"""
      |INSERT INTO monitor (
      |  manufacturer, name, product_id, vendor_id)
      |VALUES ( ?, ?, ?, ? )""".stripMargin

    val prep = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
    prep.setString(1, manufacturer)
    prep.setString(2, name)
    prep.setInt(3, productId)
    prep.setInt(4, vendorId)
    prep.executeUpdate
    val id = getLastInsertedId(prep)
    val monitor = Monitor(Some(id), manufacturer, name, productId, vendorId)

    modelObjects = modelObjects + (classOf[Monitor] -> monitor)
    monitor
  }

  /** Retrieves the class from the map, or calls the 'create' function if the value does not exist.
    */
  private def defaultObject[T](key: Class[T], create: => T): T = {
    modelObjects get key match {
      case Some(obj) => key.cast(obj)
      case None => create
    }
  }

  /** Returns the last created user, or creates one if it does not exist.
    */
  def defaultUser(implicit conn: Connection): User = {
    defaultObject(classOf[User], createUser)
  }

  /** Returns the last created container, or creates one if it does not exist.
    */
  def defaultContainer(implicit conn: Connection): Container = {
    defaultObject(classOf[Container], createContainer)
  }

  /** Returns the last created monitor, or creates one if it does not exist.
    */
  def defaultMonitor(implicit conn: Connection): Monitor = {
    defaultObject(classOf[Monitor], createMonitor)
  }
}
