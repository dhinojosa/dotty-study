package com.evolutionnext.conversions

case class Fahrenheit(value:Int)
case class Celcius(value:Int)

object Conversions extends App {

  import scala.language.implicitConversions

  delegate fahrenheitToCelcius for Conversion[Fahrenheit,Celcius] = new Conversion[Fahrenheit, Celcius] {
    def apply(f:Fahrenheit) = Celcius(((f.value.toDouble - 32) * (5.0/9.0)).round.toInt)
  }

  delegate celciusToFahrenheit for Conversion[Celcius,Fahrenheit] = new Conversion[Celcius, Fahrenheit] {
    def apply(c:Celcius) = Fahrenheit(((c.value.toDouble * (9.0/5.0)) + 32).round.toInt)
  }

  println(Celcius(0):Fahrenheit)
  println(Celcius(100):Fahrenheit)
  println(Fahrenheit(100):Celcius)
}
