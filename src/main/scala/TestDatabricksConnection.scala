import akka.stream.alpakka.slick.scaladsl.SlickSession

import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration.DurationInt

object TestDatabricksConnection {

  def main(args : Array[String]) = {
    implicit val ec : ExecutionContext = ExecutionContext.global
    val slickSession = SlickSession.forConfig("db")
    import slickSession.profile.api._
    val db = slickSession.db

    val query = sql"SELECT 1".as[Int]
    val result = Await.result(db.run(query), 10.seconds)
    println(s"""Result of "SELECT 1" is `$result`""")
  }

}