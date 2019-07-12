package com.evolutionnext.enums

enum Tree[+A] {
    case Node[A](left:Tree[A], right:Tree[A]) extends Tree[A]
    case Leaf[A](value:A) extends Tree[A]
    case Empty extends Tree[Nothing]
}

object TreeEnum extends App {
   import Tree._
   val branch1 = Node(Leaf(20), Leaf(80))
   val branch2 = Node(Leaf(90), Leaf(88))
   val branch3 = Node(Leaf(190), Leaf(883))
   val result = Node(branch1, Node(branch2, branch3))
   println(result)
}