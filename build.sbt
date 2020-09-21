name := "REST-Scala"

version := "0.1"

scalaVersion := "2.13.3"

val AkkaVersion = "2.6.9"
val AkkaHttpVersion = "10.2.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.slick" %% "slick" % "3.3.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
  "org.postgresql" % "postgresql" % "42.2.5",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion
)