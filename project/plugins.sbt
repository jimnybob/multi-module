
// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository

resolvers ++= Seq(
  Resolver.url("bintray-sbt-plugins", url("https://dl.bintray.com/sbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns),
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/"
  //"rps-dev" at "http://rps-dev.c66.me:8081/content/groups/everything/"
)


// Use the Play sbt plugin for Play projects
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.2")

// web plugins

addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-less" % "1.1.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-jshint" % "1.0.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-rjs" % "1.0.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % "1.1.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-mocha" % "1.1.0")

//addSbtPlugin("com.pharmpress" % "common-dependencies" % "1.0.50")

//addSbtPlugin("com.pharmpress" % "common-build" % "0.19")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.3")

addSbtPlugin("com.github.ddispaltro" % "sbt-reactjs" % "0.5.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-aspectj" % "0.10.4")

addSbtPlugin("com.gilt.sbt" % "sbt-aws-lambda" % "0.4.2")