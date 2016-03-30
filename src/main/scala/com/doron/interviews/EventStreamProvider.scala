package com.doron.interviews

import net.liftweb.json.Serialization.read
import net.liftweb.json._
import rx.lang.scala.Observable

import scala.util.Try

/**
 * Created by dorony on 28/03/2016.
 */

trait IEventStreamProvider {
  def fromDataStream(dataStream: Observable[String]): Observable[Event]
}

class EventStreamProvider extends IEventStreamProvider {
  implicit val formats = DefaultFormats

  def fromDataStream(dataStream: Observable[String]): Observable[Event] = {
    dataStream.map(l => parseJson(l)).filter(p => p.isSuccess).map(p => p.get)
  }

  private def parseJson(l: String): Try[Event] = {
    Try {
      read[Event](l)
    }
  }
}

case class Event(event_type: String, data: String, timestamp: Long)
