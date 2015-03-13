package com.svartsjo.scalautil

import java.util.Date

object Sleeper {
  def sleepCancellably(sleepMillis: Long, sleepWakeInterval: Long = 1000)(implicit cancelled : () => Boolean): Unit = {
    val stopSleepTime : Long = System.nanoTime()/1e6.toLong + sleepMillis

    import math._

    def sleepTimeLeft = abs(new Date().getTime() - stopSleepTime)

    while (sleepTimeLeft > 0) {
      if (cancelled())
        return
      else
        Thread.sleep(min(sleepWakeInterval, sleepTimeLeft))
    }
  }
}