name := "reactive-maps"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.1",
  "com.typesafe.akka" %% "akka-contrib" % "2.3.1",
  "de.grundid.opendatalab" % "geojson-jackson" % "1.1",
  "org.webjars" % "bootstrap" % "3.0.0",
  "org.webjars" % "knockout" % "2.3.0",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.webjars" % "leaflet" % "0.7.2"
)

lazy val root = (project in file(".")).addPlugins(PlayJava)
