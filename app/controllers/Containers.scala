package controllers

import play.api._

import play.api.mvc._

import play.api.data._
import play.api.data.Forms._

import play.api.i18n.{Messages}

import play.api.libs.json._

import anorm._

import models._
import views._

import utils.DoubleFormat._
import utils.CSV._
import utils.Email._

import java.util.{Date}

/**
 * Manage container related operations.
 */
object ContainerController extends Controller with Secured {

  /**
   * Display the dashboard.
   */
  def Home = Redirect(routes.ContainerController.list(0, 1, ""))

  def index = IsAuthenticated { username => _ =>
    User.findByEmail(username).map { user =>
      Home
    }.getOrElse(Forbidden)
  }

  val editContainerForm = {
    Form(
      mapping(
        "name" -> nonEmptyText,
        "temperatureExpected" -> optional(double),
        "temperatureRange" -> optional(double),
        "readFrequency" -> optional(longNumber),
        "monitor" -> optional(longNumber)
      )(ContainerEditData.apply)(ContainerEditData.unapply)
    )
  }

  val noteForm = Form(
    mapping(
      "note" -> nonEmptyText
    )(ContainerNote.apply)(ContainerNote.unapply)
  )

  def createContainerForm(user: User) = {
    Form(
      mapping(
        "name" -> nonEmptyText,
        "index" -> longNumber.verifying(
          Messages("Container number must be unique"),
          index => !Container.findUnderAdminByIndex(index, user.email).isDefined
        ),
        "temperatureExpected" -> optional(double),
        "temperatureRange" -> optional(double),
        "readFrequency" -> optional(longNumber),
        "monitor" -> optional(longNumber)
      )(ContainerCreateData.apply)(ContainerCreateData.unapply)
    )
  }

  // -- Actions
  /**
   * Display the paginated list of containers.
   *
   * @param page Current page number (starts from 0)
   * @param orderBy Column to be sorted
   * @param filter Filter applied on container names
   */
  def list(page: Int, orderBy: Int, filter: String) = IsAuthenticated { 
    username => implicit request =>
    User.findByEmail(username).map { user =>
      Ok(
        html.list(
          Container.findUnderAdmin(
            email=username,
            page = page,
            filter=("%"+filter+"%"),
            orderBy=orderBy
          ),
          orderBy, 
          filter, 
          user
        )
      )
    }.getOrElse(Redirect(routes.Application.login))
  }

  /**
    * Display container's data.
    *
    * @param id ID of the container to view
    */
  def details(index: Long, page: Int, orderBy: Int) = IsAuthenticated { 
    username => _ =>
    User.findByEmail(username).map { user =>
      Container.findUnderAdminByIndex(index, user.email).map { container =>
        Ok(
          html.containerPage(
            Container.getReadings(
              index=index,
              user=user.email,
              page=page,
              orderBy=orderBy
            ),
            orderBy,
            container,
            user,
            index
          )
        )
      }.getOrElse(NotFound)
    }.getOrElse(Forbidden)
  }

  /**
   * Display the 'edit form' of a existing Container.
   *
   * @param id ID of the container to edit
   */
  def edit(index: Long) = IsAuthenticated { username => _ =>
    User.findByEmail(username).map { user =>
      Container.findUnderAdminByIndex(index, user.email).map { container =>
        Ok(
          html.editForm(
            index, 
            editContainerForm.fill(Container.toEdit(container)),
            Monitor.options,
            user
          )
        )
      }.getOrElse(NotFound)
    }.getOrElse(Forbidden)
  }
  
  /**
   * Handle the 'edit form' submission 
   *
   * @param id ID of the container to edit
   */

  def update(index: Long) = IsAuthenticated { 
    username => implicit request =>
    User.findByEmail(username).map { user =>
      editContainerForm.bindFromRequest.fold(
        formWithErrors =>
          BadRequest(
            html.editForm(
              index,
              formWithErrors,
              Monitor.options,
              user
            )
          ),
        container => {
          Container.update(index, user.email, container)
          Redirect(routes.ContainerController.details(index, 0, 1))
        }
      )
    }.getOrElse(Forbidden)
  }

  def editNote(index: Long, readID: Long) = IsAuthenticated {
    username => implicit request =>
    User.findByEmail(username).map { user =>
      Container.findUnderAdminByIndex(index, user.email).map { container =>
        Container.getContainerReading(container, readID).map { reading =>
          Ok(
            html.editNoteForm(
              index,
              reading.readID,
              reading.readTemperature,
              reading.readStatus,
              reading.readTime.toString,
              reading.readNote,
              noteForm,
              user
            )
          )
        }.getOrElse(NotFound)
      }.getOrElse(NotFound)
    }.getOrElse(Forbidden)
  }

  def updateNote(index: Long, readID: Long) = IsAuthenticated {
    username => implicit request =>
    User.findByEmail(username).map { user =>
      Container.findUnderAdminByIndex(index, user.email).map { container =>
        Container.getContainerReading(container, readID).map { reading =>
          noteForm.bindFromRequest.fold(
            formWithErrors => {
              Redirect(routes.ContainerController.details(index, 0, 1))
            },
            noteData => {
              Container.addNote(container.id, readID, noteData.note)
              recordNote(index, user.email, reading.readTemperature,
                reading.readStatus, reading.readTime, noteData.note)
              Redirect(routes.ContainerController.details(index, 0, 1))
            }
          )
        }.getOrElse(NotFound)
      }.getOrElse(NotFound)
    }.getOrElse(Forbidden)
  }


  /**
   * Display the 'new container form'.
   */
  def create = IsAuthenticated { username => _ =>
    User.findByEmail(username).map { user =>
      Ok(html.createForm(createContainerForm(user), Monitor.options, user))
    }.getOrElse(Forbidden)
  }

  /**
   * Handle the 'new container form' submission.
   */
  def save = IsAuthenticated { username => implicit request =>
    User.findByEmail(username).map { user =>
      createContainerForm(user).bindFromRequest.fold(
        formWithErrors => BadRequest(
          html.createForm(formWithErrors,
          Monitor.options, 
            user
          )
        ),
        containerCreate => {
          Container.insert(containerCreate, Seq(username))
          Home.flashing("success" -> "Container has been created")
        }
      )
    }.getOrElse(Forbidden)
  }
  
  /**
   * Handle container deletion.
   */
  def delete(index: Long) = IsAuthenticated { username => _ =>
    User.findByEmail(username).map { user =>
      Container.delete(index, user.email)
      Home.flashing("success" -> "Container has been deleted")
    }.getOrElse(Forbidden)
  }

  def clientPackage(index: Long) = Action { request =>
    request.body.asJson.map { credentials =>
      (credentials \ "email").asOpt[String].map { email =>
        (credentials \ "password").asOpt[String].map { password =>
          if (User.valid(email, password)) {
            Container.toJson(index, email).map { jsObj =>
              Ok(Json.stringify(jsObj))
            }
          }.getOrElse { NotFound }
          else Forbidden

        }.getOrElse { BadRequest("Missing parameter: password") }
      }.getOrElse { BadRequest("Missing parameter: email") }
    }.getOrElse { BadRequest("Expecting JSON authentication credentials") }
  }

  def handleUpload(index: Long) = Action { request =>
    request.body.asJson.map { json =>
      val email = (json \ "email").asOpt[String].getOrElse{""}
      val password = (json \ "password").asOpt[String].getOrElse{""}
      if (!User.valid(email, password)) Forbidden
      else {
        (json \ "temperature").asOpt[Double].map { temperature =>
          (json \ "status").asOpt[String].map { status =>
            val time = new Date()
            Container.addReading(index, email, temperature, status, time)

            if ((status contains "WARNING") | (status contains "ERROR")) {
              val container = Container.findUnderAdminByIndex(index, email).get
              val message: String = container.name +" #" + index.toString + " has reported the following error: \n\n" + status + "\n\n recording a temperature of " + temperature.toString + "C on " + time.toString + "."

              // figure out what's wrong with adminsOf and then send email to 
              // every admin of the freezer
              User.getByListNum(User.getListNum(email)).map { user =>
                sendEmail(user.email, "Freezer Warning", message)
              }
            }
              // record this reading to the container's CSV
            recordReading(index, email, temperature, status, time)

            Ok("Update complete\n")
          }.getOrElse { BadRequest("Missing parameter: status\n") }
        }.getOrElse { BadRequest("Missing parameter: temperature\n") }
      }
    }.getOrElse { BadRequest("Expecting JSON data\n") }
  }
}
