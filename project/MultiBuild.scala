import sbt.Keys._
import sbt._

object MultiBuild extends Build
{
  /**
    * When running locally multiple microservices using 2 or more instances of sbt and when a source file
    * changes, it triggers full builds of all the microservices. This is due to each sbt instance compiling the changed
    * class which in turn makes other sbt instances think that the compiled classes changed, triggering a compilation
    * of more files, eventually leading to full builds.
    *
    * Also intellij uses the same target directory like sbt. Sometimes intellij gets confused by sbt-compiled classes.
    *
    * This method optionally modifies the target directory for sbt (and intellij) so that multiple builds will use
    * different target directories, avoiding the issue.
    *
    * use as per:
    *
    * sbt -Dmodule=content
    * sbt -Dmodule=fc
    *
    * to start sbt which will compile under content or fc subdirs.
    *
    * Within intellij configure sbt i.e. with -Dmodule=ide
    */
  def targetDirs = System.getProperty("module") match {
    case null =>
      Seq.empty
    case module =>
      println(s"====> module is $module . Will compile all classes under $module subdir")
      Seq(
      target := target.value / module
    )
  }

  lazy val commonSettings = Seq(
    version := "100.0.0-SNAPSHOT",
    resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
    resolvers += "Smartii snapshots" at "http://repo.smartii.co.uk:8081/nexus/content/repositories/snapshots",
    resolvers += "Smartii releases" at "http://repo.smartii.co.uk:8081/nexus/content/repositories/releases"
  ) ++ targetDirs


  lazy val multi = (project in file(".")).aggregate(
    alexaSmarthomeModel,
    smartiiHomeAlexa,
    smartiiIr,
    lightwaveAkka
  ).settings(aggregate in update := false).settings(commonSettings)


  lazy val alexaSmarthomeModel = project in file("alexa-smarthome-model") settings commonSettings

  lazy val smartiiHomeAlexa = project in file("smartii-home-alexa") dependsOn(alexaSmarthomeModel) settings commonSettings

  lazy val smartiiIr = project in file("smartii-ir") settings commonSettings

  lazy val lightwaveAkka = project in file("lightwave-akka") settings commonSettings
}
