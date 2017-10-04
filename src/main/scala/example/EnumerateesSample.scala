package example

import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Execution.Implicits.{ defaultExecutionContext => dec }
import play.api.libs.iteratee.Enumerator
import play.api.libs.iteratee.Enumeratee
import scala.concurrent.Future

object EnumerateesSample extends App {
  val sum: Iteratee[Int, Int] = Iteratee.fold[Int, Int](0) { (s, e) => s + e }
  val strings: Enumerator[String] = Enumerator("1", "2", "3", "4")
  //create am Enumeratee using the map method on Enumeratee
  val toInt: Enumeratee[String, Int] = Enumeratee.map[String] { s => s.toInt }
  val adaptedIteratee: Iteratee[String, Int] = toInt.transform(sum)
  val toIntFuture: Future[Iteratee[String, Int]] = strings |>> adaptedIteratee
  val eventuallyResult: Future[Int] = toIntFuture.flatMap(i => i.run);
  //Eventually print the result
  eventuallyResult.onComplete { case x => println(x) }
}