package utils

object timeUtils {

  def roundTime(seconds: Double): Long = {
    if (seconds < 0) 0 else seconds.toLong
  }

}
