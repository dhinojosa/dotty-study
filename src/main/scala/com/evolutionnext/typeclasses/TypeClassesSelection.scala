package com.evolutionnext.typeclasses 

trait AShow[A] {
   def show(a:A):String
}
class Artist(val firstName:String, val lastName:String)

object MyPredef {
   delegate showArtistFirstThenLast for AShow[Artist] = new AShow[Artist] {
      def show(a:Artist):String = s"Artist(${a.firstName} ${a.lastName})"
   }
   delegate showArtistLastThenFirst for AShow[Artist] = new AShow[Artist] {
      def show(a:Artist):String = s"Artist(${a.lastName}, ${a.firstName})"
   }
}

object TypeClassesSelection extends App {
  import delegate MyPredef.showArtistLastThenFirst
  val result:String = the[AShow[Artist]].show(Artist("Lashana","Lynch"))
  println(result)
}
