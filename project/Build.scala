import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "zorglux"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "com.google.guava" % "guava" % "13.0.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
    )

}
