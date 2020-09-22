package Controller

import Controller.JsonHandler.createJson
import Model.{ClientData, DatabaseManager, MyListener}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.io.StdIn
import scala.util.Success

object Server {

  def start(): Unit = {

    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext = system.executionContext

    lazy val db = new DatabaseManager

    val route = {
      concat(
        (get & pathPrefix("")) {
          (pathEndOrSingleSlash & redirectToTrailingSlashIfMissing(StatusCodes.TemporaryRedirect)) {
            getFromResource("web/index.html")
          } ~ {
            getFromResourceDirectory("web")
          }
        },
        path("table") {
          concat (get {
            val listener = new MyListener {
              override def success(s: String): Unit = println("")
            }

            onComplete(db.listOfTableElements(listener)) {
              case Success( value) => complete(s" ${createJson(value)}")
            }
          },
          post{
            complete("asd")
          })
        })

    }

    val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}

object Main extends App {
  Server.start()
}

