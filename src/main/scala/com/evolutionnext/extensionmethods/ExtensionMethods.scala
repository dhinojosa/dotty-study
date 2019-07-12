package com.evolutionnext.extensionmethods

object ExtensionMethods extends App {
   /* Currently there is no isOdd() with
    * Int, so we can create one with an 
    * extension method */
  def (i:Int) isOdd: Boolean = i % 2 != 0
  def (i:Int) isEven: Boolean = !i.isOdd

  println(40.isOdd)
  println(40.isEven)
}
