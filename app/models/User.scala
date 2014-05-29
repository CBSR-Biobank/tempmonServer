package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class User(email: String, name: String, password: String)

case class UserEmail(email: String)

object User {
  // -- Parsers
  
  /**
   * Parse a User from a ResultSet
   */
  val simple = {
    get[String]("user.email") ~
    get[String]("user.name") ~
    get[String]("user.password") map {
      case email~name~password => User(email, name, password)
    }
  }
  
  val emailOnly = {
    get[String]("user.email") map {
      case email => UserEmail(email)
    }
  }

  // -- Queries
  
  /**
   * Retrieve a User from email.
   */
  def findByEmail(email: String): Option[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from user where email = {email}").on(
        'email -> email
      ).as(User.simple.singleOpt)
    }
  }
  
  /**
   * Retrieve all users.
   */
  def findAll: Seq[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from user").as(User.simple *)
    }
  }
  
  /**
   * Authenticate a User.
   */
  def authenticate(email: String, password: String): Option[User] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
         select * from user where 
         email = {email} and password = {password}
        """
      ).on(
        'email -> email,
        'password -> password
      ).as(User.simple.singleOpt)
    }
  }

  def valid(email: String, password: String): Boolean = {
    DB.withConnection { implicit connection =>
      val valid = SQL(
        """
          SELECT EXISTS( 
            SELECT u.email, 
                   u.password 
            FROM user as u

            WHERE email = {email}
            AND password = {password}
          );
        """
      ).on(
        'email -> email,
        'password -> password
      ).as(scalar[Long].single)

      (valid == 1)
    }
  }
   
  /**
   * Create a User.
   */
  def create(user: User): User = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into user values (
            {email}, {name}, {password}
          )
        """
      ).on(
        'email -> user.email,
        'name -> user.name,
        'password -> user.password
      ).executeUpdate()
      
      user

    }
  }

  def getListNum(email: String): Int = {
    // retrieve the list number of the user with given email; returns the user's
    // list number if found or 0 if not
    User.findByEmail(email).map { user =>
      DB.withConnection { implicit connection =>
        SQL (
          """
           SELECT u.container_list_number FROM user as u where u.email = {email}
          """
        ).on('email -> email).as(scalar[Int].single)
      }
    }.getOrElse(0)
  }

  def getByListNum(listNum: Long): List[UserEmail] = {
    DB.withConnection { implicit connection =>
      val users = SQL (
        """
          select u.email
          from user as u

          where u.container_list_number = {list_num}
        """
      ).on(
        'list_num -> listNum
      ).as(User.emailOnly *)

      return users
    }
  }
}
