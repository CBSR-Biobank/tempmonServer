package utils

import models._

import org.joda.time.DateTime
import com.github.nscala_time.time.Imports._
import java.nio.file.{ Path, Paths, Files }
import com.github.tototoshi.csv._

import scala.io.Source
import java.io._
import java.util.regex._
import java.text.SimpleDateFormat

import play.api.Play.current

object CSV {
  val projectRoot: String = current.path.toString()

  val dateFormat = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss z yyyy")

  /*
   * Escape line breaks, double quotes and commas in the given string with
   * double quotes
   */
  def escapeCharacters(
    toEscape: String
  ): String = {
    var stb = new StringBuilder()

    toEscape.map { char =>
      if (char == ',' || char == '\n' || char == '\r') {
        stb.append('"')
        stb.append(char)
        stb.append('"')
      }
      else if (char == '"') {
        stb.append('"')
        stb.append(char)
      }
      else {
        stb.append(char)
      }
    }
    stb.mkString
  }

  def recordReading (
    id: Long,
    email: String,
    temperature: Double,
    status: String,
    time: DateTime
  ) {
    val listNum = User.getListNum(email)
    if (listNum != 0) {
      val year = time.year
      val month = time.month
      val day = time.day

      var path = s"$projectRoot/logs/container-lists/$listNum/containers/$id/$year/$month/"

      Files.createDirectories(Paths.get(path))
      path = s"$path$day-$month-$year.csv"

      val writer = CSVWriter.open(path, append = true)
      writer.writeRow(List(temperature.toString, status, dateFormat.print(time)))
      writer.close()
    }
  }

  def recordNote (
    index: Long,
    email: String,
    temperature: Double,
    status: String,
    time: DateTime,
    note: String
  ) = {
    val listNum = User.getListNum(email)
    if (listNum != 0) {
      val year = time.year
      val month = time.month
      val day = time.day
      val path = s"$projectRoot/logs/container-lists/$listNum/containers/$index/$year/$month/"
      val fullPath = s"$path$day-$month-$year.csv"
      val escapedNote = escapeCharacters(note)

      try {
        val file = scala.io.Source.fromFile(fullPath)
        val linesIn = file.getLines.toArray
        file.close()

        val replace = s"$temperature,$status,${dateFormat.print(time)}"

        val linesOut = linesIn.map { line =>
          if (line.startsWith(replace)) { s"$line,$escapedNote" } else { line }
        }

        val out = new PrintWriter(fullPath);
        linesOut.foreach { line => out.println(line) }
        out.close()
      }
      catch {
        case e: IOException => play.api.Logger.error("Failed writing to CSV: File Not Found")
      }
    }
  }
}
