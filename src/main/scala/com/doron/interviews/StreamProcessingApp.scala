package com.doron.interviews

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHolder
import org.eclipse.jetty.util.log.{Log, StdErrLog}
import org.eclipse.jetty.webapp.WebAppContext

/**
 * Created by dorony on 28/03/2016.
 */

object StreamProcessingApp {

  def main(args: Array[String]): Unit = {
    if (args.length < 1) {
      println("please specify executable to run.")
      return
    }
    val cmd = args(0)

    val dataObservable = new ProcessDataStreamProvider(cmd).getDataStream()
    val eventStream = new EventStreamProvider().fromDataStream(dataObservable)
    val eventStatsHolder = new EventStatsHolder
    val processor = new EventProcessor(eventStatsHolder)
    processor.beginProcessing(eventStream)
    startHttpServer(eventStatsHolder)
  }

  private def startHttpServer(eventStatsHolder: IEventStatsHolder): Unit = {
    val server = new Server(8080)
    val root = new WebAppContext()
    root.setResourceBase("src/web")
    root.addServlet(new ServletHolder(new EventStatsServlet(eventStatsHolder)), "/")
    server.setHandler(root)

    Log.setLog(new StdErrLog())
    server.start()
  }
}
