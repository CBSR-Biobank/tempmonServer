package models

import java.sql.{ Connection, Statement, PreparedStatement }
import org.joda.time.DateTime
import com.github.nscala_time.time.Imports._
import org.joda.time.format.DateTimeFormat

/** Uses the JDBC interfance, and not Anorm or Slick, to insert rows into the database. This object
  * is used for testing and should access the database independently of how the application accesses
  * it.
  */
object DbUtils {

  /** Updates the container list number in the user table for the given user.
    */
  def updateUserContainerListNumber(user: User, containerIndex: Int)(implicit conn: Connection) = {
    val sql = s"UPDATE user SET container_list_number=$containerIndex where email='${user.email}'"
    val stmt = conn.createStatement
    stmt.executeUpdate(sql)
  }

  /** Creates a row in the "container_list" table for the given container.
    */
  def createContainerListItem(
    container: Container,
    containerIndex: Int)(implicit conn: Connection): Long = {
    val sql = s"""
      |INSERT INTO container_list (list_number, container_index, container_id)
      |VALUES (?, ?, ?)""".stripMargin
    val stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
    stmt.setInt(1, 1)
    stmt.setInt(2, containerIndex)
    stmt.setLong(3, container.id.get)
    stmt.executeUpdate
    Factory.getLastInsertedId(stmt)
  }

  /** Creates a container linked to the given user.
    */
  def createContainerLikedToUser(user: User)(implicit conn: Connection): Container = {
    val containerIndex = 1
    val container = Factory.createContainer
    updateUserContainerListNumber(user, containerIndex)
    createContainerListItem(container, containerIndex)
    container
  }

  /** Reads a container reading that maches the criteria. Returns None if nothing is found.
    */
  def containerLastReading(
    container: Container)(implicit conn: Connection): Option[ContainerReading] = {
    val timeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS")
    val sql = s"""
      |SELECT * FROM container_readings
      |WHERE container_id=${container.id.get}
      |ORDER BY read_time DESC""".stripMargin
    val stmt = conn.createStatement
    val rs = stmt.executeQuery(sql)

    if (rs.next) {
      val id = rs.getInt("reading_id")
      val containerId = rs.getInt("container_id")
      val readTemperature = rs.getDouble("read_temperature")
      val readStatus = rs.getString("read_status")
      val readTime = new DateTime(rs.getTimestamp("read_time"))
      val readNote = rs.getString("read_note")
      Some(ContainerReading(Some(id), readTemperature, readStatus, readTime))
    } else {
      None
    }
  }

}
