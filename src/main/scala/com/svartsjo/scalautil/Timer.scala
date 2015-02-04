package com.svartsjo.scalautil

object Timer {
  def time[A](f: => A)(resultHandler: (Double) => Unit): A = {
    val t0 = System.nanoTime()
    val res = f
    val time = (System.nanoTime() - t0)/1e9*1.0;
    resultHandler(time)
    res
  }
}