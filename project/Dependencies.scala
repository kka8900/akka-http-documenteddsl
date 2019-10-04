import sbt._

object Dependencies {
  val AkkaHttpVersion = "10.1.9"
  val AkkaVersion = "2.5.25"
  val akkaHttpCore      = "com.typesafe.akka"   %% "akka-http-core"       % AkkaHttpVersion
  val akkaHttp          = "com.typesafe.akka"   %% "akka-http"            % AkkaHttpVersion
  val akkaHttpTestKit   = "com.typesafe.akka"   %% "akka-http-testkit"    % AkkaHttpVersion % Test
  val akkaStream        = "com.typesafe.akka"   %% "akka-stream"          % AkkaVersion
  val akkaStreamTestkit = "com.typesafe.akka"   %% "akka-stream-testkit"  % AkkaVersion % Test
  val akkaHttpPlayJson  = "com.evolutiongaming" %% "akka-http-play-json"  % "0.1.13"
  val jsonSchema        = "com.sauldhernandez"  %% "autoschema"           % "1.0.4"
  val scalaTest         = "org.scalatest"       %% "scalatest"            % "3.0.8" % Test
  val mockito           = "org.mockito"          % "mockito-core"         % "3.1.0" % Test
}