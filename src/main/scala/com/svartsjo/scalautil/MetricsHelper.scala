package com.svartsjo.scalautil

import java.util.concurrent.TimeUnit
import com.codahale.metrics.ConsoleReporter
import com.codahale.metrics.MetricRegistry

trait MetricsHelper {
  import com.codahale.metrics._

  val metricsRegistry = new MetricRegistry()

  private def name(names: String*) = MetricRegistry.name(this.getClass(), names: _*)

  // checked metric code, looks to be thread safe
  def getMeter(names: String*) = metricsRegistry.meter(name(names: _*))
  def getTimer(names: String*) = metricsRegistry.timer(name(names: _*))
  def getCounter(names: String*) = metricsRegistry.counter(name(names: _*))

  def time[A](f: => A, timer: com.codahale.metrics.Timer): A = {
    val context = timer.time()
    try {
      f
    } finally {
      context.stop()
    }
  }

  def startSimpleConsoleReporter() {
    ConsoleReporter.forRegistry(metricsRegistry).build().start(5, TimeUnit.SECONDS)
  }
}