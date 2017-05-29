name := "kafkaspark"

version := "1.0"

scalaVersion := "2.11.11"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.1"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.11" % "2.1.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.1.1"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.1.0"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.1"

