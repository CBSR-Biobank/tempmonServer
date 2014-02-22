package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current
import play.api.libs.json._

import anorm._
import anorm.SqlParser._

case class Monitor(id: Pk[Long], manufacturer: String, name: String, 
  productID: Long, vendorID: Long)
  
object Monitor {
  val defaultID = 1
  /**
   * Parse a Monitor from a ResultSet
   */
  val simple = {
    get[Pk[Long]]("monitor.id") ~
    get[String]("monitor.manufacturer") ~
    get[String]("monitor.name") ~
    get[Long]("monitor.product_id") ~
    get[Long]("monitor.vendor_id") map {
      case id~manufacturer~name~productID~vendorID => 
        Monitor(id, manufacturer, name, productID, vendorID)
    }
  }
  
  /**
   * Construct the Map[String,String] needed to fill a select options set.
   */
  def options: Seq[(String,String)] = DB.withConnection { implicit connection =>
    SQL("select * from monitor order by name").as(Monitor.simple *).map(c =>
      c.id.toString -> c.name)
  }

  def findByID(id: Long): Option[Monitor] = {
    DB.withConnection { implicit connection =>
      SQL("select * from monitor where id = {id}").on(
        'id -> id
      ).as(Monitor.simple.singleOpt)
    }
  }

  def toJson(id: Long): Option[JsObject] = {
    Monitor.findByID(id).map { monitor =>
      Json.obj(
        "name" -> monitor.name,
        "manufacturer" -> monitor.manufacturer,
        "productID" -> monitor.productID.toString,
        "vendorID" -> monitor.vendorID.toString
      )
    }
  }
}
