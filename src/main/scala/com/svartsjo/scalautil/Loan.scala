package com.svartsjo.scalautil

// http://illegalexception.schlichtherle.de/2012/07/19/try-with-resources-for-scala/
// http://docs.oracle.com/javase/specs/jls/se7/html/jls-14.html#jls-14.20.3.1

import java.io._

class Loan[A <: AutoCloseable](resource: A) {
  def to[B](block: A => B) = {
    var t: Throwable = null
    try {
      block(resource)
    } catch {
      case x : Throwable => t = x; throw x
    } finally {
      if (resource != null) {
        if (t != null) {
          try {
            resource.close()
          } catch {
            case y : Throwable => t.addSuppressed(y)
          }
        } else {
          resource.close()
        }
      }
    }
  }
}

object Loan {
  def loan[A <: AutoCloseable](resource: A) = new Loan(resource)
}