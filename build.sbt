ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

val scalaJsVersion = "3.2.17"

lazy val testbaseJvm = (project in file("testbase/jvm"))
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest"          % scalaJsVersion,
      "org.scalatest" %%% "scalatest-flatspec" % scalaJsVersion
    ),
    Compile / unmanagedSourceDirectories += (file("testbase/src/main/scala").getAbsoluteFile)
  )

lazy val testbaseJs = (project in file("testbase/js"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %%% "scalatest"          % scalaJsVersion,
      "org.scalatest" %%% "scalatest-flatspec" % scalaJsVersion
    ),
    Compile / unmanagedSourceDirectories += (file("testbase/src/main/scala").getAbsoluteFile)
  )

lazy val user = (project in file("user"))
  .dependsOn(testbaseJvm % Test)

lazy val root = (project in file("."))
  .settings(
    name := "intellij_multi_project_bug"
  )
  .aggregate(
    testbaseJvm,
    testbaseJs,
    user
  )
