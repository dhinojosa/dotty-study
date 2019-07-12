package com.evolutionnext.abstractdatatypes

/* An enum is perfect for an abstract data type */
enum Tree[+T] {
  case Branch(left: Tree[T], right:Tree[T])
  case Leaf(value: T)
}

/* The above will create all the basic extensions
 * required. For example:
 *
 * enum Tree[+T] {
 *   case Branch(left: Tree[T], right: Tree[T]) extends Tree[T]
 *   case Leaf(value: T) extends Tree[T]
 * }
 *
 * If we had an object that represented Nothing it would work and it
 * would be created implicitly.  The given rule is that parameterized
 * type T would need to be covariant with (+)
 *
 * enum Tree[+T] {
 *   case Branch(left: Tree[T], right: Tree[T]) extends Tree[T]
 *   case Leaf(value: T) extends Tree[T]
 *   case Empty extends Tree[Nothing]
 * }
 **/

 object AbstractDataTypes extends App {
  val tree = 
     Tree.Branch(left =  
                   Tree.Branch(left = 
                                 Tree.Leaf(40), 
                               right = 
                                 Tree.Branch(Tree.Leaf(20), 
                                             Tree.Leaf(100))) 
                 , right =
                     Tree.Branch(left =
                                   Tree.Leaf(20), 
                                 right = 
                                   Tree.Leaf(60))
                )
  println(tree)
}

