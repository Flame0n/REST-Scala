import java.io.PrintWriter

import Model.{ClientDataModel, DatabaseManager}
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.protobufv3.internal.compiler.PluginProtos.CodeGeneratorResponse.File
import javax.swing.text.html.HTML

import scala.io.Source
import scala.io.StdIn

object HttpServerRoutingMinimal {

  def main(): Unit = {

    implicit val system = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext = system.executionContext

    def getHomePageHtml: String = {
      var s: String = ""

      var src = Source.fromFile("src\\main\\scala\\HomePage.html").getLines()

      for (i <- src) {
        s += i
      }

      return s
    }

    val route =
      path("") {
        get {

          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, getHomePageHtml))
        }
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


  val db = new DatabaseManager

  db.incert(ClientDataModel(10,firstName = "asd", lastName = "sdf", address = "asdasdasd", birthDate = "asdasdasd"))
  db.showTable

  db.deleteById(10)

  //Thread.sleep(1)

  db.showTable

  HttpServerRoutingMinimal.main()


}