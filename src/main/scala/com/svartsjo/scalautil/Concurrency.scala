package com.svartsjo.scalautil

import java.util.concurrent.ExecutorService
import scala.concurrent.ExecutionContext

object Concurrency {
  def createCloneOfDefaultExecutionContext() = {
    import scala.language.reflectiveCalls
    type ECI = { def createExecutorService: ExecutorService }
    val executor = ExecutionContext.Implicits.global.asInstanceOf[ECI].createExecutorService
    (ExecutionContext.fromExecutor(executor), executor)
  }
}