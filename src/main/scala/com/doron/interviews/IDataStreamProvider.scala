package com.doron.interviews


import rx.lang.scala._
import rx.schedulers.Schedulers

import scala.sys.process._

/**
 * Created by dorony on 28/03/2016.
 */
trait IDataStreamProvider {
  def getDataStream(): Observable[String]
}

class ProcessDataStreamProvider(processName: String) extends IDataStreamProvider {

  override def getDataStream(): Observable[String] = {
    val cmd = Seq(processName)
    Observable
      .from(cmd.lineStream)
      .subscribeOn(JavaConversions.javaSchedulerToScalaScheduler(Schedulers.newThread()))
  }
}
