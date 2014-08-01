package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.i18n.{Messages}
import play.api.libs.json._
import org.joda.time.DateTime
import anorm._
import org.joda.time.DateTime
import org.joda.time.format.{ DateTimeFormat, DateTimeFormatter }

import models._
import views._

import utils.DoubleFormat._
import utils.CSV._
import utils.Email._

/*
 * Manage container related operations.
 */
object ContainerController extends Controller with Secured {

  val timeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

  def dateTimeToLocalTime(time: DateTime): String = {
    timeFormatter.print(time);
  }

  /*
   * Display the dashboard.
   */
  def Home = Redirect(routes.ContainerController.list(0, 1, ""))

  def index = IsAuthenticated { username => _ =>
    User.getByEmail(username).map { user =>
      Home
    }.getOrElse(Forbidden)
  }

  /*
   * Form for modifying a container; index is absent since various constructs
   * rely on a container maintaining the same index (i.e. CSV writing)
   */
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

  /*
   * Made a form for notes instead of just using a single since in the future
   * the note section may become more robust, wanted to be able to add to it
   * easily.
   */
  val noteForm = Form(
    mapping(
      "note" -> nonEmptyText
    )(ContainerNote.apply)(ContainerNote.unapply)
  )

  /*
   * Form for creating a new container. In reality clients won't function
   * without a temperatureExpected/Range, read frequency or monitor but I left
   * them as optional for reasons I can't really recall.
   *
   * @param user User making request (so no duplicate indices may be submitted)
   */
  def createContainerForm(user: User) = {
    Form(
      mapping(
        "name" -> nonEmptyText,
        "index" -> longNumber.verifying(
          Messages("Container number must be unique"),
          index => !Container.getOwnedByAdminByIndex(index, user.email).isDefined
        ),
        "temperatureExpected" -> optional(double),
        "temperatureRange" -> optional(double),
        "readFrequency" -> optional(longNumber),
        "monitor" -> optional(longNumber)
      )(ContainerCreateData.apply)(ContainerCreateData.unapply)
    )
  }

  // -- Actions
  /*
   * Display the paginated list of containers.
   *
   * @param page Current page number (starts from 0)
   * @param orderBy Column to be sorted
   * @param filter Filter applied on container names
   */
  def list(page: Int, orderBy: Int, filter: String) = IsAuthenticated {
    username => implicit request =>
    User.getByEmail(username).map { user =>
      Ok(
        html.list(
          Container.getOwnedByAdmin(
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

  /*
   * Display container's data.
   *
   * @param index Index of the container to view
   * @param page Page number of the container's readings
   * @param orderBy Unused
   */
  def details(
    index: Long,
    page: Int,
    filterErrors: Int,
    orderBy: Int
  ) = IsAuthenticated {
    username => _ =>
    User.getByEmail(username).map { user =>
      Container.getOwnedByAdminByIndex(index, user.email).map { container =>
        Ok(
          html.containerPage(
            Container.getReadings(
              index=index,
              user=user.email,
              page=page,
              orderBy=orderBy,
              filterErrors=filterErrors
            ),
            orderBy,
            container,
            user,
            index,
            filterErrors
          )
        )
      }.getOrElse(NotFound)
    }.getOrElse(Forbidden)
  }

  /*
   * Display the 'edit form' of a existing Container.
   *
   * @param index Index of the container to edit
   */
  def edit(index: Long) = IsAuthenticated { username => _ =>
    User.getByEmail(username).map { user =>
      Container.getOwnedByAdminByIndex(index, user.email).map { container =>
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

  /*
   * Handle the 'edit form' submission
   *
   * @param index Index of the container to edit
   */
  def update(index: Long) = IsAuthenticated {
    username => implicit request =>
    User.getByEmail(username).map { user =>
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

  /*
   * Display the edit note form for a reading
   *
   * @param index Index of the container with reading to edit
   * @param readID ID of the reading to edit
   */
  def editNote(index: Long, readID: Long) = IsAuthenticated {
    username => implicit request =>
    User.getByEmail(username).map { user =>
      Container.getOwnedByAdminByIndex(index, user.email).map { container =>
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

  /*
   * Handle the update note form submission
   *
   * @param index Index of the container with the reading to edit
   * @param readID ID of the reading to edit
   */
  def updateNote(index: Long, readID: Long) = IsAuthenticated {
    username => implicit request =>
    User.getByEmail(username).map { user =>
      Container.getOwnedByAdminByIndex(index, user.email).map { container =>
        Container.getContainerReading(container, readID).map { reading =>
          noteForm.bindFromRequest.fold(
            formWithErrors => {
              Redirect(routes.ContainerController.details(index, 0, 1))
            },
            noteData => {
              Container.addNote(container.id, readID, noteData.note)
              recordNote(index, user.email, reading.readTemperature,
                reading.readStatus, reading.readTime, noteData.note)
              Redirect(routes.ContainerController.details(index, 0, 0))
            }
          )
        }.getOrElse(NotFound)
      }.getOrElse(NotFound)
    }.getOrElse(Forbidden)
  }


  /*
   * Display the 'new container form'.
   */
  def create = IsAuthenticated { username => _ =>
    User.getByEmail(username).map { user =>
      Ok(html.createForm(createContainerForm(user), Monitor.options, user))
    }.getOrElse(Forbidden)
  }

  /*
   * Handle the 'new container form' submission.
   */
  def save = IsAuthenticated { username => implicit request =>
    User.getByEmail(username).map { user =>
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

  /*
   * Handle container deletion.
   *
   * @param index Index of the container to delete
   */
  def delete(index: Long) = IsAuthenticated { username => _ =>
    User.getByEmail(username).map { user =>
      Container.delete(index, user.email)
      Home.flashing("success" -> "Container has been deleted")
    }.getOrElse(Forbidden)
  }

  /*
   * Serve client with JSON object containing runtime specifications
   *
   * @param index Index of the container to turn to JSON
   */
  def JSONify(index: Long) = IsAuthenticated { username => _ =>
    Container.toJson(index, username).map { jsObj =>
      Ok(Json.stringify(jsObj))
    }.getOrElse(NotFound)
  }

  /*
   * Handle a JSON reading upload from a client process
   *
   * @param index Index of container the reading is for
   */
  def addReading(index: Long) = IsAuthenticated { username => request =>
    request.body.asJson.map { json =>
      val time = DateTime.now
        (json \ "temperature").asOpt[Double].map { temperature =>
          val status = Container.getStatus(index, username, temperature)
          if (status contains "WARNING") {
            Container.alarm(index, username, temperature, status, time)
          }
          Container.addReading(index, username, temperature, status, time)

          // record this reading to the container's CSV
          recordReading(index, username, temperature, status, time)

          Ok("Update complete\n")
        }.getOrElse {
          (json \ "error").asOpt[String].map { error =>
            val message = "ERROR: " + error
            Container.alarm(index, username, message, time)

            Container.addReading(index, username, -10000, message, time)

            // record this error to the container's CSV; using 0 is easier than
            // making an entirely new class of functions for recording errors
            recordReading(index, username, -10000, message, time)

            Ok("Update complete\n")
          }.getOrElse {
            BadRequest("Bad parameters\n")
          }
        }
    }.getOrElse { BadRequest("Expecting JSON data\n") }
  }
}
