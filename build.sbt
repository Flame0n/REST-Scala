name := "REST-Scala"

version := "0.1"

scalaVersion := "2.13.3"

val AkkaVersion = "2.6.9"
val AkkaHttpVersion = "10.2.0"


Runtime / unmanagedClasspath += baseDirectory.value / "src/main/resources"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "net.liftweb" %% "lift-json" % "3.4.2",
  "org.postgresql" % "postgresql" % "42.2.5",
  "org.slf4j" % "slf4j-nop" % "1.6.4"
)