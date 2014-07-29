package utils

import com.typesafe.plugin._
import play.api.Play.current

object Email {
  def sendEmail(
    recipient: String,
    title: String,
    message: String
  ) = {
    // figure out what's wrong with adminsOf and then send email to every
    // admin of the freezer
    val mail = use[MailerPlugin].email
    mail.setRecipient(recipient)
    mail.setSubject(title)
    mail.setFrom("cbsrtempmon@gmail.com")

    mail.send(message)
  }
}
