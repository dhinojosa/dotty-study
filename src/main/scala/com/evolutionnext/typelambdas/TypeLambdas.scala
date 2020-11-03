package com.evolutionnext.typelambdas

object TypeLambdas extends App {

  /** A type lambda is a partial application of types in type constructors
  *  where the types are defined. Previous to dotty looked like the following:
  *  new T[({type l[A]=SomeType[A,..]})#l] 
  *
  *  The type lambda can be used for type projection, so given the definition for 
  *  a Monad[M[A]], notice that it takes a certain shape:
  **/

  trait Monad[M[_]] {
    def point[A](a: A): M[A]
    def bind[A, B](m: M[A])(f: A => M[B]): M[B]
  }

  /** We cannot use say, the either type since it has a different shape, 
  * namely Either[L, R].  We can either do a projection like the
  * following where we will define the left, but not the right,
  * thus rendering a new type with the M[A] shape: 
  **/

  type MyEither[A] = Either[String, A]

  /** This will allow for the shape to nicely fit into the Monad 
  * as a type class.
  **/

  type F1 = Monad[MyEither]

  /** If we attempted to inline the above definition, then it would not
  * work.
  */

  //type F2 = Monad[MyEither[String, A]] //Fail

  /** If we didn't want to construct the new type we could've done a type
  * lambda trick and that uses {type λ[α] = Either[A, α]})#λ which looks
  * scary. But what it means is the same thing as before Either[A, α] where A
  * will be the generic type and α will be the right projection.
  **/

  type F2 = Monad[({type λ[α] = Either[String, α]})#λ]

  /** If you are wondering how we use F2 then we can one instantiate this new
   * type.
   *
   **/
  val f2 = new F2 {
   def point[A](a: A): Either[String, A] = Right(a)
   def bind[A, B](a: Either[String, A])(f: A => Either[String, B]) = a.flatMap(f)
  }

  /** But where we can mainly use it is in a method where it is required, and
  * some of the other types are unknown.
  *
  */
  def foo[M[_,_], A](a:A)(md:Monad[({type λ[α] = M[String, α]})#λ]):M[String, A]  = 
     md.bind(md.point(a))(x => md.point(x))


  /** Using the above method can be invoked with the following **/
  foo[Either,Int](30)(f2)
  //println(foo(30)(f2))

  /** That unfortunately was too much work so type lambdas syntax arrives in Dotty 
   *  so as to take care of the situtation with out much of the ugly syntax
   **/

  type F3 = Monad[[X] =>> Either[String, X]]

  val f3 = new F3 {
    def point[A](a:A):Either[String, A] = Right(a)
    def bind[A,B](a:Either[String, A])(f: A => Either[String, B]) = a.flatMap(f)
  }

  def bar[M[_, _], A](a: A)(using md: Monad[[A] =>> Either[String, A]]) =
    md.bind(md.point(a))(x => md.point(x))

  given me as Monad[[A] =>> Either[String, A]] =
    new Monad[[A] =>> Either[String, A]]:
      def point[A](a: A): Either[String, A] = Right(a)
      def bind[A, B](a: Either[String, A])(f: A => Either[String, B]) = a.flatMap(f)


  println(f3.bind(Right(4))(x => Right(x + 40)))
  println(bar(40))
}

  /**
* Attempting to declare it all within the method
def process3(hkt:Z[M[_,_], A]):Int = ???
* The above can be refactored into
def process4[M[_, _],A](hkt:M[String,A]):Int = ???
*/



//Note from older version of Scala
// 
// trait Monad[M[_]] {
  //   def point[A](a: A): M[A]
  //   def bind[A](m: M[A])(f: A => M[A]): M[A]
  // }
  // 
  // type F1= Monad[({type λ[α] = Either[String, α]})#λ]
  // 
  // val f1 = new F1 {
    //   def point[A](a:A):Either[String, A] = Right(a)
    //   def bind[A](a:Either[String, A])(f: A => Either[String, A]) = a.flatMap(f)
    // }
    // 
    // def foo[M[_,_], A](a:A)(md:Monad[({type λ[α] = M[String, α]})#λ]) = md.bind(md.point(a))(x => md.point(x))
    // 
    // 
    // implicit val me:Monad[({type λ[α] = Either[String, α]})#λ] = new Monad[({type λ[α] = Either[String, α]})#λ] {
      //   def point[A](a:A):Either[String, A] = Right(a)
      //   def bind[A](a:Either[String, A])(f: A => Either[String, A]) = a.flatMap(f)
      // }
      // 
      // println(f1.bind(Right(4))(x => Right(x + 40)))
      // 
      // println(foo(40)(f1))
      // }
