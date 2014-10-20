import sbt._
import Keys._

object WebappDeploy extends Build {
  import Dependency._
  import Resolvers._
  import BuildSettings._
  import scala.collection._

  lazy val root = Project(id = "mysite", base = file("."), settings = standardBuildSettings ++ Seq(
    resolvers := Seq(jettyRepo, resolver, Classpaths.typesafeReleases),
    libraryDependencies ++= jetty ++ scalaTest ++ lift
  ))
}

object BuildSettings {

  val dist = taskKey[Unit]("dist")

  val standardBuildSettings: Seq[Def.Setting[_]] = Defaults.coreDefaultSettings ++ Seq[Setting[_]](
      organization := "mysite",
      version := "1.0",
      scalaVersion := "2.10.4",

    mappings in (Compile, packageBin) ++= {
      val webapp: File = baseDirectory.value / "src/main/webapp"
      for ((from, to) <- (webapp ***) x rebase(webapp, "webapp")) yield (from, to)
    },

    dist <<= (baseDirectory, target, packageBin in Compile, dependencyClasspath in Compile) map {
      (base, targetDir, artifact, libs) =>

        val jars = libs.map(_.data) x flat
        val script = file("mysite.sh") x flat
        val files = Seq(
            artifact                  -> "mysite.jar"
        )
      IO.zip(files ++ jars ++ script, targetDir / "dist.zip")
    }
  )
}

object Resolvers {
  val jettyRepo = "jetty repo" at "http://siasia.github.com/maven2"
  val resolver = "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
}

object Dependency {
  private val jettyVersion = "8.1.7.v20120910"
  private val scalaTestVersion = "2.1.3"
  private val liftwebVersion = "2.5.1"
  private val liftwigetsVersion = "2.4-M1"
  private val seleniumVersion = "2.35.0"
  private val scalaXmlVersion = "1.0.2"

  val jetty = Seq (
      "org.eclipse.jetty" % "jetty-server" % jettyVersion,
      "org.eclipse.jetty" % "jetty-webapp" % jettyVersion
    )

  val lift = Seq(
    "net.liftweb" %% "lift-webkit" % liftwebVersion exclude("org.scala-lang", "scala-compiler")
  )

  val scalaTest = Seq(
    "org.scalatest" % "scalatest_2.10" % scalaTestVersion  % "test",
    "org.seleniumhq.selenium" % "selenium-java" % seleniumVersion % "test"
  )
}
