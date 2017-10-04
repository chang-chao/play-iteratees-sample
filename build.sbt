import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "me.chanagchao",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "play-iteratees-sample",
    libraryDependencies += scalaTest % Test
  )

libraryDependencies += "com.typesafe.play" %% "play-iteratees" % "2.6.1"
