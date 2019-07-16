package com.evolutionnext.givendelegates

object ExecutionContextDelegate extends App {
  import scala.concurrent._
  import java.util.concurrent.Executors
  delegate ec for ExecutionContext = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(12))

  val future = Future {
    println(s"Thread-name: ${Thread.currentThread().getName}")
    Thread.sleep(3000)
    50 + 100
  }

  future.foreach(println)
}
