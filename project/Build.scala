import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "scalaserv"
  val appVersion      = "0.1"

  val appDependencies = Seq(
    // Add your project dependencies here,
    filters,
    jdbc,
    anorm,
    "mysql" % "mysql-connector-java" % "5.1.28",
    "com.typesafe.play" %% "play-slick" % "0.5.0.8",
    "com.typesafe" %% "play-plugins-mailer" % "2.2.0",
    "com.github.tototoshi" %% "scala-csv" % "1.0.0"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
