package com.evolutionnext.typeclasses 

trait Show[A] {
   def show(a:A):String
}
case class Employee(firstName:String, lastName:String)

delegate showEmployeeFirstThenLast for Show[Employee] = new Show[Employee] {
   def show(a:Employee):String = s"Employee(${a.firstName} ${a.lastName})"
}

object TypeClasses extends App {
  val result:String = the[Show[Employee]].show(Employee("Lashana","Lynch"))
  println(result)
}
