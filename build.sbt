name := "stream-processing"

version := "1.0"

scalaVersion := "2.11.8"

javaHome  := Some(file("C:\\Program Files\\Java\\jdk1.8.0_72"))

libraryDependencies += "io.reactivex" % "rxscala_2.11" % "0.26.0"

libraryDependencies += "org.scalatra" % "scalatra_2.11" % "2.4.0"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "9.3.8.v20160314"

libraryDependencies += "net.liftweb" % "lift-json_2.11" % "2.6.3"
