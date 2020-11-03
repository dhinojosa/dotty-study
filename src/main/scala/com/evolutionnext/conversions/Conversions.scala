package com.evolutionnext.conversions

case class Fahrenheit(value:Int):
  override def toString = s"$value°F"

case class Celcius(value:Int):
  override def toString = s"$value°C"

object Conversions extends App:

  import scala.language.implicitConversions

  given fahrenheitToCelcius as Conversion[Fahrenheit,Celcius]:
    def apply(f:Fahrenheit) = 
      Celcius(((f.value.toDouble - 32) * (5.0/9.0)).round.toInt)

  given celciusToFahrenheit as Conversion[Celcius,Fahrenheit]:
    def apply(c:Celcius) = 
      Fahrenheit(((c.value.toDouble * (9.0/5.0)) + 32).round.toInt)

  def diffFromFreezing(c:Celcius):Celcius = 
    println("processing:" + c)
    val result = if (c.value < 0) Celcius(0) else c
    println("result:" + result)
    result

  printf("0°C in Fahrenheit is %s\n", Celcius(0):Fahrenheit)
  printf("100°C in Fahrenheit is %s\n", Celcius(100):Fahrenheit)
  printf("100°F in Celcius is %s\n", Fahrenheit(100):Celcius)
