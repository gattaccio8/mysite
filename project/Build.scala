import sbt._
import Keys._

object WebappDeploy extends Build {
  import Dependency._
  import Resolvers._
  import BuildSettings._
  import scala.collection._

  lazy val root = Project(id = "webappDeploy", base = file("."), settings = standardBuildSettings ++ Seq(
    resolvers := Seq(jettyRepo, resolver, Classpaths.typesafeResolver),
    libraryDependencies ++= jetty ++ scalaTest
  ))
}

object BuildSettings {

  val dist = taskKey[Unit]("dist")

  val standardBuildSettings: Seq[sbt.Project.Setting[_]] = Defaults.defaultSettings ++ Seq[Setting[_]](
      organization := "webappDeploy",     //TODO these 3 lines are ignored here and picked from build.sbt instead
      version := "1.0",
      scalaVersion := "2.10.4",

      dist <<= (baseDirectory, target, packageBin in Compile) map {
      (base, targetDir, artifact) =>
        val files = Seq(
        artifact           ->  "lib/webappDeploy.jar"
        )
        IO.zip(files, targetDir / "dist.zip")
    }
  )
}

object Resolvers {
  val jettyRepo = "jetty repo" at "http://siasia.github.com/maven2"
  val resolver = "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
}
object Dependency {
  private val jettyVersion = "8.1.7.v20120910"
  private val scalaTestVersion = "2.2.1"

  val jetty = Seq (
      "org.eclipse.jetty" % "jetty-server" % jettyVersion,
      "org.eclipse.jetty" % "jetty-webapp" % jettyVersion
    )

  val scalaTest = Seq("org.scalatest" % "scalatest_2.11" % scalaTestVersion  % "test")
}
