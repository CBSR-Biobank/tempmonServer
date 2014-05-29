package utils

import models._

import java.util.{Date}
import java.util.{Calendar}
import java.nio.file.{Path, Paths, Files}
import com.github.tototoshi.csv._

import scala.io.Source
import java.io._
import java.util.regex._
import java.text.SimpleDateFormat

object CSV {
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
    time: Date
  ) {
    val listNum = User.getListNum(email)
    if (listNum != 0) {
      val c: Calendar = Calendar.getInstance()
      c.setTime(time)

      val year = c.get(Calendar.YEAR).toString
      // calendar month off by 1, January = 0th month according to Java devs
      val month = (c.get(Calendar.MONTH) + 1).toString
      val day = c.get(Calendar.DAY_OF_MONTH).toString

      var path: String = "/home/connor/workspace/ScalaTempmon/logs/container-lists/"+listNum.toString+"/containers/"+id.toString+"/"+year+"/"+month+"/"

      Files.createDirectories(Paths.get(path))
      path = path+day+"-"+month+"-"+year+".csv"

      val writer = CSVWriter.open(path, append = true)
      writer.writeRow(List(temperature.toString, status, time.toString))
      writer.close()
    }
  }

  def recordNote (
    index: Long,
    email: String,
    temperature: Double,
    status: String,
    time: Date,
    note: String
  ) = {
    val listNum = User.getListNum(email)
    if (listNum != 0) {
      val c: Calendar = Calendar.getInstance()
      c.setTime(time)

      val year = c.get(Calendar.YEAR).toString
      // calendar month off by 1, January = 0th month according to Java devs
      val month = (c.get(Calendar.MONTH) + 1).toString
      val day = c.get(Calendar.DAY_OF_MONTH).toString

      var path: String = "/home/connor/workspace/ScalaTempmon/logs/container-lists/"+listNum.toString+"/containers/"+index.toString+"/"+year+"/"+month+"/"

      val fullPath = path+day+"-"+month+"-"+year+".csv"

      val df = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy")
      val readTime = df.format(c.getTime())

      val escapedNote = escapeCharacters(note)

      try {
        val file = scala.io.Source.fromFile(fullPath)
        val linesIn = file.getLines.toArray
        file.close()

        val replace = temperature.toString+","+status+","+readTime
        val replacement = temperature.toString+","+status+","+readTime+","+escapedNote

        val linesOut = linesIn.map {
          case line if line.startsWith(replace) => replacement;
          case x => x
        }

        val out = new PrintWriter(fullPath);
        linesOut.foreach { line => out.println(line) }
        out.close()
      }
      catch {
        case e: IOException => println("adsflkdfas")
      }
    }
  }
}
