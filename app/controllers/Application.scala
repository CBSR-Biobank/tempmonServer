package controllers

import anorm._

import views._
import models._
import utils.DoubleFormat._

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

/**
  * Manage and control container, monitors, users.
  */
object Application extends Controller with Secured
{
  val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> nonEmptyText
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => User.authenticate(email, password).isDefined
    })
  )

  /* 
   * serve user with login form
   */
  def login = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  /*
   * Verify user's login credential submission is valid. If not, redirect
   * to login page indicating as much
   */
  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.login(formWithErrors)),
      user => Redirect(routes.ContainerController.index).withSession(
        "email" -> user._1
      )
    )
  }

  /*
   * Log the user out and return them to the login page
   */
  def logout = Action {
    Redirect(routes.Application.login).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }

  /*
   * Effectively strips the trailing '/' from a URL, for example if a user
   * tries to go to /containers/ since no such route exists redirect them
   * to /containers
   */
  def untrail(path: String) = Action {
    MovedPermanently("/" +path)
  }

}

/*
 * Provide security features
 */
trait Secured
{
  /*
   * Retrieve the connected user email.
   */
  private def username(request: RequestHeader) = request.session.get("email")

  /*
   * Redirect to login if the user in not authorized.
   */
  private def onUnauthorized(request: RequestHeader) =
    Results.Redirect(routes.Application.login)
  
  /*
   * Redirect user to requested action with certified authentication credentials
   */
  def withAuth(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action(request => f(user)(request))
    }
  }

  /*
   * Perform a given request verifying the user is logged in 
   */
  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = {
    Security.Authenticated(username, onUnauthorized) { user =>
      Action { request =>
        f(user)(request)
      }
    }
  }

}

