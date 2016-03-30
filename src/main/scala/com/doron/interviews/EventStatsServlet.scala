package com.doron.interviews

import net.liftweb.json._
import net.liftweb.json.Serialization.write
import org.scalatra.ScalatraServlet

/**
 * Created by dorony on 29/03/2016.
 */
class EventStatsServlet (eventStatsHolder: IEventStatsHolder) extends ScalatraServlet{
  implicit val formats = DefaultFormats
  get("/") {
    write(eventStatsHolder.getStats())
  }

}
