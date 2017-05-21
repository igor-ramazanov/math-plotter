name := "compilers-theory"

version := "1.0"

scalaVersion := "2.12.2"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.6",
  "org.scalafx" %% "scalafxml-core-sfx8" % "0.3"
)