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
    version := "100.0.0-kostas-SNAPSHOT",
    resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
  ) ++ targetDirs


  lazy val multi = (project in file(".")).aggregate(
//    basecampProject,
//    commonPlayProject,
//    fc,
    alexaSmarthomeModel,
//    fcModelProject,
    smartiiIr
//    metricsProject,
//    exportRulesApplicatorProject,
//    fcAdminService,
//    searchService,
//    contentService,
//    ExporterProject,
//    dmdRemoteProject,
//    apiAppJsonProject,
//    DataManagerProject,
//    batchProject,
//    batchCommonProject,
//    pcc
  ).settings(aggregate in update := false).settings(commonSettings)

//  lazy val contentService = project in file ("content-service") dependsOn (
//    fcModelProject % "test->test;compile->compile",
//    bnfModelProject % "test->test;compile->compile",
//    commonElasticSearchProject % "test->test;compile->compile",
//    commonPlayProject,
//    batchCommonProject,
//    exportRulesApplicatorProject
//    ) settings commonSettings
//
//  lazy val searchService = project in file ("search-service") dependsOn (
//      fcModelProject,
//      bnfModelProject % "test->test;compile->compile",
//      commonElasticSearchProject % "test->test;compile->compile"
//    ) settings commonSettings
//
//  lazy val fcAdminService = project in file ("fc-admin-service") dependsOn (
//    fcModelProject % "test->test;compile->compile",
//    bnfModelProject,
//    commonElasticSearchProject % "test->test;compile->compile"
//    ) settings commonSettings
//
//  lazy val fc = project in file("fc") dependsOn (
//      bnfModelProject % "test->test;compile->compile",
//      fcModelProject % "test->test;compile->compile",
//      metricsProject,
//      exportRulesApplicatorProject,
//      commonElasticSearchProject % "test->test"
//    ) settings commonSettings

  lazy val alexaSmarthomeModel = project in file("alexa-smarthome-model") settings commonSettings

//  lazy val fcModelProject = project in file("fc-model") dependsOn bnfModelProject settings commonSettings
//
//  lazy val exportRulesApplicatorProject = project in file("export-rules-applicator") dependsOn (bnfModelProject % "test->test;compile->compile") settings commonSettings
//
//  lazy val metricsProject = project in file("metrics") settings commonSettings

  lazy val smartiiIr = project in file("smartii-ir") settings commonSettings

//  lazy val ExporterProject = project in file("Exporter") dependsOn(
//    commonElasticSearchProject,
//    bnfModelProject % "test->test;compile->compile",
//    fcModelProject,
//    exportRulesApplicatorProject,
//    commonPlayProject
//    ) settings commonSettings
//
//  lazy val apiAppJsonProject=project in file("api-app-json") dependsOn (
//    bnfModelProject % "test->test;compile->compile",
//    exportRulesApplicatorProject
//    ) settings commonSettings
//
//  lazy val DataManagerProject = project in file("DataManager") dependsOn(
//    apiAppJsonProject,
//    commonElasticSearchProject,
//    bnfModelProject % "test->test;compile->compile",
//    commonPlayProject
//    ) settings commonSettings
//
//  lazy val dmdRemoteProject = project in file("dmdRemote") dependsOn(
//    bnfModelProject,
//    commonElasticSearchProject,
//    basecampProject,
//    commonPlayProject
//    ) settings commonSettings
//
//  lazy val basecampProject = project in file("basecamp") settings commonSettings
//
//  lazy val commonPlayProject = project in file("common-play") settings commonSettings
//
//  lazy val batchProject = project in file("batch") dependsOn(
//    DataManagerProject,
//    ExporterProject,
//    dmdRemoteProject,
//    batchCommonProject
//    ) settings commonSettings
//
//  lazy val batchCommonProject = project in file("batchCommon") dependsOn(
//    commonElasticSearchProject % "test->test;compile->compile"
//    ) settings commonSettings
//
//  lazy val pcc = project in file("publishing-control-centre") dependsOn(
//    bnfModelProject % "test->test;compile->compile",
//    commonElasticSearchProject,
//    batchCommonProject,
//    commonPlayProject
//    ) settings commonSettings
}
