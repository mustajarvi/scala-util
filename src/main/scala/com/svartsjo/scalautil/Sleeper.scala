package com.svartsjo.scalautil

import java.util.Date

object Sleeper {
  def sleepCancellably(sleepMillis: Long, sleepWakeInterval: Long = 1000)(implicit cancelled : () => Boolean): Unit = {
    val stopSleepTime : Long = new java.util.Date().getTime + sleepMillis

    import math._

    def sleepTimeLeft = stopSleepTime - new Date().getTime()

    while (!cancelled() && sleepTimeLeft > 0) {
        Thread.sleep(min(sleepWakeInterval, sleepTimeLeft))
    }
  }
}