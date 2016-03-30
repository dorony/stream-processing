package com.doron.interviews

import rx.lang.scala.Observable

/**
 * Created by dorony on 28/03/2016.
 */
class EventProcessor (eventStatsHolder: IEventStatsHolder) {

  def beginProcessing (eventStream: Observable[Event]) = {
    eventStream.subscribe(e => processNewEvent(e))
  }

  private def processNewEvent(event: Event) = {
    val oldStats = eventStatsHolder.getStats()
    val newStats = oldStats.updated(event)
    eventStatsHolder.setStats(newStats)
  }
}
