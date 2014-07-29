package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import java.util.{Date}
import java.security._

import scala.util._

case class User(email: String, name: String, password: String)

case class UserEmail(email: String)

case class UserCreateData(email: String, nameFirst: String, nameLast: String, password: String, birthday: String, gender: String, country: String)

case class AdminAddData(email: String)

object User {
  // -- Parsers
  
  /**
   * Parse a User from a ResultSet
   */
  val simple = {
    get[String]("user.email") ~
    get[String]("user.name_first") ~
    get[String]("user.name_last") ~
    get[String]("user.password") map {
      case email~name_first~name_last~password => User(email, name_first+" "+name_last, password)
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
   * 
   * @param email Email of user in question
   */
  def getByEmail(email: String): Option[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from user where email = {email}").on(
        'email -> email
      ).as(User.simple.singleOpt)
    }
  }
  
  /*
   * Basically get a user by email, but with the stipulation that they have 
   * the given container list number. Saves having to query whether or not
   * the user found by email has a container list number.
   * 
   * @param email Email of user to return
   * @param listNum Container list number of user
   */
  def getByEmailWithListNum(email: String, listNum: Long) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          SELECT * FROM user 

          WHERE email = {email} 
          AND container_list_number = {listNum}
        """
      ).on(
        'email -> email,
        'listNum -> listNum
      ).as(User.simple.singleOpt)
    }
  }

  /**
   * Retrieve all users.
   */
  def getAll: Seq[User] = {
    DB.withConnection { implicit connection =>
      SQL("select * from user").as(User.simple *)
    }
  }
  
  /**
   * Authenticate a User.
   * 
   * @param email Submitted email value
   * @param password Submitted password value
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

  /**
   * Verify that the given email/password pair are valid
   * 
   * @param email Email to verify
   * @param password Password to verify
   */
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
   * 
   * @param user Data required to create a new user
   */
  def create(user: UserCreateData) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into user (
            email,
            name_first,
            name_last,
            password,
            birthday,
            gender,
            country
          ) values (
            {email}, 
            {name_first}, 
            {name_last},
            {password},
            {birthday},
            {gender},
            {country}
          )
        """
      ).on(
        'email -> user.email,
        'name_first -> user.nameFirst,
        'name_last -> user.nameLast,
        'password -> user.password,
        'birthday -> user.birthday,
        'gender -> user.gender,
        'country -> user.country
      ).executeUpdate()
      
      user

    }
  }

  /*
   * retrieve the list number of the user with given email; returns the user's
   * list number if found or 0 if not (not very functional, I know)
   * 
   * @param email Email to search for
   */
  def getListNum(email: String): Int = { 
    User.getByEmail(email).map { user =>
      DB.withConnection { implicit connection =>
        SQL (
          """
           SELECT u.container_list_number FROM user as u where u.email = {email}
          """
        ).on('email -> email).as(scalar[Int].single)
      }
    }.getOrElse(0)
  }

  /*
   * Get all users with given container list number
   * 
   * @param listNum Container list number to search by
   */
  def getByListNum(listNum: Long): List[UserEmail] = {
    DB.withConnection { implicit connection =>
      val users = SQL (
        """
          select u.email
          from user as u

          where u.container_list_number = {list_num}
          AND u.email != "admin@cbsrtempmon.ca"
        """
      ).on(
        'list_num -> listNum
      ).as(User.emailOnly *)

      return users
    }
  }

  /*
   * Add an admin, i.e. a user 
   */
  def addAdmin(admin: String, listNum: Long) = {
    DB.withConnection { implicit connection =>
      SQL (
        """
          insert into user (
            email,
            name_first,
            name_last,
            password,
            birthday,
            gender,
            country,
            container_list_number
          ) values (
            {email}, 
            "Fake",
            "Name",
            {password},
            "1-2-3",
            "O",
            "CA",
            {listNum}
          );
          """
      ).on(
        'email -> admin,
        'password -> "",
        'listNum -> listNum
      ).executeUpdate()
    }
  }

  def delete(admin: String) = {
    DB.withConnection { implicit connection =>
      SQL (
        """
          delete from user

          where email = {userEmail}
        """
      ).on(
        'userEmail -> admin
      ).executeUpdate()
    }
  }
}
