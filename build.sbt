name := "heremapsapi-speed-alerts"

version := "0.1"

scalaVersion := "2.12.13"
val TypesafeConfigVersion = "1.4.1"
val AkkaVersion = "2.6.13"
val AkkaHttpVersion = "10.2.4"

lazy val typesafeConfig = "com.typesafe" % "config" % TypesafeConfigVersion
lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % AkkaVersion
lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % AkkaVersion
lazy val akkaHttp = "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion

libraryDependencies ++= Seq(
  typesafeConfig,
  akkaActor,
  akkaStream,
  akkaHttp
)