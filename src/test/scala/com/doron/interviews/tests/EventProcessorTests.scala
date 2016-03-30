package com.doron.interviews.tests

import com.doron.interviews._
import org.scalatest.FunSuite
import rx.lang.scala.Observable

/**
 * Created by dorony on 30/03/2016.
 */
class EventProcessorTests extends FunSuite {

  test("beginProcessing => some events to process => stats updated") {
    val statsHolder = new EventStatsHolder()
    val processor = new EventProcessor(statsHolder)
    val events = Observable.from(List(Event("type1", "data1", 1), Event("type1", "data1", 1), Event("type2", "data2", 1)))
    processor.beginProcessing(events)
    assert(statsHolder.getStats() === EventStats(Map("type1" -> 2, "type2" -> 1), Map("data1" -> 2, "data2" -> 1)))
  }
}
