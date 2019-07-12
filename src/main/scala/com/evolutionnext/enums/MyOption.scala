package com.evolutionnext.enums

object MyOption extends App {
  enum MyOption[+T] {
    case MySome(x: T) extends MyOption[T]
    case None         extends MyOption[Nothing]
  }

  val m:MyOption[String] = MyOption.MySome("Scala")

  import MyOption._

  m match {
    case MySome(x) => s"MySome($x)"
    case None => s"None"
  }

  val answer = MySome(42) //enums are widened
}