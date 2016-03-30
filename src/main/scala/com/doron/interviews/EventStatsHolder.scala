package com.doron.interviews

/**
 * Created by dorony on 28/03/2016.
 */

trait IEventStatsHolder {
  def getStats() : EventStats

  def setStats(eventStats: EventStats)
}

class EventStatsHolder extends IEventStatsHolder {
  var stats = EventStats(Map(), Map())

  def getStats(): EventStats = stats

  def setStats(eventStats: EventStats) = {
    stats = eventStats
  }
}

case class EventStats(eventTypeStats: Map[String, Int], dataStats: Map[String, Int]) {
  def updated(event: Event) : EventStats = {
    val newEventTypeStats = eventTypeStats.updated(event.event_type, eventTypeStats.get(event.event_type).getOrElse(0) + 1)
    val newDataStats = dataStats.updated(event.data, dataStats.get(event.data).getOrElse(0) + 1)
    EventStats(newEventTypeStats, newDataStats)
  }
}
