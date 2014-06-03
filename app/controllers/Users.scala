package controllers

import play.api._

import play.api.mvc._

import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import play.api.i18n.{Messages}

import play.api.libs.json._

import anorm._

import models._
import views._

import utils.DoubleFormat._
import utils.CSV._

import java.util.{Date}

object UserController extends Controller with Secured {

  def Home = Redirect(routes.Application.login)
  def ContainerPage = Redirect(routes.ContainerController.list(0, 1, ""))
  val createUserForm = {
    Form(
      mapping(
        "name_first" -> nonEmptyText,
        "name_last" -> nonEmptyText,
        "email" -> nonEmptyText.verifying(
          Messages("Email not available"),
          email => !User.findByEmail("email").isDefined
        ),
        "password" -> tuple(
          "main" -> text(minLength = 6),
          "confirm" -> text
        ).verifying(
          // Add an additional constraint: both passwords must match
          Messages("Passwords don't match"),
          passwords => passwords._1 == passwords._2
        ).transform[String](
          {passwords => passwords._1}, 
          {password => password -> password}
        ),

        "birthday" -> nonEmptyText,
        "gender" -> nonEmptyText,
        "country" -> nonEmptyText
      )(UserCreateData.apply)(UserCreateData.unapply)
    )
  }

  def addAdminForm(user: User) = {
    Form (
      mapping(
        "email" -> email.verifying(
          Messages("Admin is already a member of this list"),
          email => !User.findByEmailWithListNum(
            email, 
            User.getListNum(user.email)
          ).isDefined
        )
      )(AdminAddData.apply)(AdminAddData.unapply)
    )
  }

  def generate() = Action { implicit request =>
    Ok(html.createUserForm(createUserForm))
  }

  def create() = Action { implicit request =>
    createUserForm.bindFromRequest.fold(
      formWithErrors => BadRequest(
        html.createUserForm(formWithErrors)
      ),
      user => {
        User.create(user)
        Home.flashing("success" -> "User created")
      }
    )
  }

  def editAdmins() = IsAuthenticated { username => implicit request =>
    User.findByEmail(username).map { user =>
      val admins = User.getByListNum(User.getListNum(user.email))
      Ok(html.editAdminForm(addAdminForm(user), user, admins))
    }.getOrElse(Forbidden)
  }

  def submitAdmin() = IsAuthenticated { username => implicit request =>
    User.findByEmail(username).map { user =>
      val admins = User.getByListNum(User.getListNum(user.email))
      addAdminForm(user).bindFromRequest.fold(
        formWithErrors => BadRequest(
          html.editAdminForm(formWithErrors, user, admins)
        ),
        admin => {
          User.addAdmin(admin.email, User.getListNum(user.email))
          Redirect(routes.UserController.editAdmins)
        }
      )
    }.getOrElse(Forbidden)
  }

  def deleteAdmin(email: String) = IsAuthenticated { 
    username => implicit request =>
    User.findByEmail(username).map { user =>
      User.delete(email)
      Redirect(routes.UserController.editAdmins)
    }.getOrElse(Forbidden)
  }
}
