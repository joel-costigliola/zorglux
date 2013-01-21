import sbt._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "zorglux"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "com.google.guava" % "guava" % "14.0-rc2",
      "org.jongo" % "jongo" % "0.3",
      "com.lordofthejars" % "nosqlunit-mongodb" % "0.7.3" % "test",
      "junit" % "junit" % "4.11" % "test",
      "org.mongodb" % "mongo-java-driver" % "2.10.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here
    )

}
