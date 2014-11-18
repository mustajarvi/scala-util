package com.svartsjo.scalautil

// http://jamesgolick.com/2010/2/8/monkey-patching-single-responsibility-principle-and-scala-implicits.html

class Tapper[A](tapMe: A) {
  def tap(f: (A) => Unit): A = {
    f(tapMe)
    tapMe
  }
}

object Tap {
  implicit def any2Tapper[A](toTap: A): Tapper[A] = new Tapper(toTap)
}

