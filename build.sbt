import PlayKeys._

name := """tempmonServer"""

organization := "org.biobank"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalacOptions ++= Seq(
  "-target:jvm-1.7",
  "-encoding", "UTF-8",
  "deprecation",        // warning and location for usages of deprecated APIs
  "-feature",           // warning and location for usages of features that should be imported explicitly
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps",
  "-unchecked",          // additional warnings where generated code depends on assumptions
  "-Xlint",
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-value-discard" // Warn when non-Unit expression results are unused
)

scalacOptions in (Compile,doc) := Seq("-groups", "-implicits")

//javaOptions ++= Seq("-Xmx1024M", "-XX:MaxPermSize=512m")

javaOptions in Test ++=  Seq(
  "-Dconfig.file=conf/test.conf",
  "-Dlogger.resource=logback-test.xml"
)

testOptions in Test := Nil

(testOptions in Test) += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/report")

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Snapshots" at "http://repo.akka.io/snapshots/"
)

libraryDependencies ++= Seq(
  cache,
  filters,
  jdbc,
  anorm,
  "mysql"                     %  "mysql-connector-java"            % "5.1.28",
  "com.typesafe.play"         %% "play-slick"                      % "0.8.0-RC2",
  "com.typesafe.play.plugins" %% "play-plugins-mailer"             % "2.3.0",
  "com.github.tototoshi"      %% "scala-csv"                       % "1.0.0",
  "com.github.nscala-time"    %% "nscala-time"                     % "1.2.0",
  "org.scalatest"             %% "scalatest"                       % "2.1.5"      % "test->*" excludeAll(
      ExclusionRule(organization = "org.junit", name = "junit")
    )
)
