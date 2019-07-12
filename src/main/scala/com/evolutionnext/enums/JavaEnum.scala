package com.evolutionnext.enums
//This defines a new sealed class, Color, with three values, 
// Color.Red, Color.Green, Color.Blue. The color values are 
// members of Colors companion object.

enum MyColor extends java.lang.Enum[MyColor] { 
    case Red
    case Green
    case Blue
}

enum A extends java.lang.Enum[A] {
  case MONDAY, TUESDAY, SATURDAY
}

enum B(val gravity: Double) extends java.lang.Enum[B] {
  case EARTH extends B(9.8)
  case JUPITER extends B(100)
  case MOON extends B(4.3)
  case Foo extends B(10)
}

enum Suit extends java.lang.Enum[Suit] {
  case HEARTS, DIAMONDS, CLOVER, SPADES
}

object JavaEnum extends App {
    val m = MyColor.Red
    println(m)
    val g = MyColor.Green
    println(g == g)

    import com.evolutionnext.enums.Planet
    val planet = Planet.MARS
    println(planet)
}