package utils

import models._
import java.util.{Date}
import java.util.{Calendar}
import java.nio.file.{Path, Paths, Files}
import com.github.tototoshi.csv._

object CSV {

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

      var path: String = "/home/connor/workspace/ScalaTempmon/logs/container-lists/"+listNum.toString+"/containers/"+id.toString+"/"+c.get(Calendar.YEAR).toString+"/"+c.get(Calendar.MONTH).toString+"/"

      Files.createDirectories(Paths.get(path))

      path = path+c.get(Calendar.DAY_OF_MONTH).toString+".csv"

      val writer = CSVWriter.open(path, append = true)

      writer.writeRow(List(temperature.toString, status, time.toString))

      writer.close()
    }
  }
}
