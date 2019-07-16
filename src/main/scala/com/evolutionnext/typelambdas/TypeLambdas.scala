package com.evolutionnext.typelambdas

object TypeLambdas extends App {

  //type Z = M[A]
  //type M = [A] =>> M[A]
  
  //def acceptHigherKindedTypes[M =>> F,A](x:M[A]) = x

  //println(acceptHigherKindedTypes(Some(123)))
  //println(acceptHigherKindedTypes(List(5, 10, 12, 50)))

  // type StringMap[V] = Map[String, V]

  //val m:StringMap[Int] = Map("Canada" -> 37000000, 
  //                           "United States" -> 308745538,
  //                           "Mexico" -> 123000000)

  //println(acceptHigherKindedTypes(m))
}
