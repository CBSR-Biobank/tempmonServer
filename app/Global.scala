import play.api._
import com.typesafe.plugin._

import akka.actor.Actor
import akka.actor.Props
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.concurrent.Akka
import play.api.Play.current
import scala.concurrent.duration._

import models._
import controllers._
import views._
import utils._

object Global extends GlobalSettings {
  def notifyUsersOfLateReadings() = {
    val containers = Container.getLateReadings()

    containers.groupBy(c => c.listNumber).foreach { containerList =>
      var message = new StringBuilder

      message.append("The following containers' readings are over an hour overdue:\r\n\r\n")
      containerList._2.foreach { container =>
        message.append(container.name)
        message.append(" #")
        message.append(container.index.toString)
        message.append("'s last reading was at ")
        message.append(container.readTime.get.toString)
        message.append("\r\n")
      }

      User.getByListNum(containerList._1).foreach { user =>
        Email.sendEmail(user.email, "Readings overdue", message.toString)
      }
    }
  }
  /*
   * Perform a check on containers, verifying no readings are overdue
   */
  override def onStart(app: Application) {
    Akka.system.scheduler.schedule(60.minutes, 60.minutes) {
      notifyUsersOfLateReadings()
    }
  }
}
