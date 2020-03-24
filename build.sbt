import sbt.Keys._

name := "scala-play-demo"

version := "0.1"

scalaVersion := "2.13.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += guice
libraryDependencies += "org.joda" % "joda-convert" % "2.2.1"
libraryDependencies += "net.logstash.logback" % "logstash-logback-encoder" % "6.3"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.6"