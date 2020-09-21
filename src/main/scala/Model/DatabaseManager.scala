package Model

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{Await, Future, blocking}

trait MyListener {
  def success(s: String)
}

class DatabaseManager {

  val user = "postgres"
  val url = "jdbc:postgresql://localhost:5432/1"
  val password = "1234"
  val driver = "org.postgresql.Driver"

  val db = Database.forURL(url, user = user, password = password, driver = driver)


  val clients = TableQuery[Clients]

  def incert(client: ClientDataModel): Unit = {
    val userId = (clients returning clients.map(_.id)) += client
    val f = db.run(userId)
    f.onComplete {
      case Success(s) => println("")
      case Failure(t) => t.printStackTrace()
    }
  }

  def deleteById(id: Long) =
    db.run(clients.filter(_.id === id).delete)

  def deleteByFirstName(firstName: String) =
    db.run(clients.filter(_.firstName === firstName).delete)

  def deleteByLastName(lastName: String) =
    db.run(clients.filter(_.lastName === lastName).delete)

  def deleteByBirthDate(lastName: String) =
    db.run(clients.filter(_.lastName === lastName).delete)

  def searchByID(id: Long) =
    db.run(clients.filter(_.id === id).result)

  def showTable: Unit = {
    val f = db.run(clients.result).map(_.foreach {
      case (client: ClientDataModel) =>
        println(client)
    })

    f.onComplete {
      case Success(s) => println(s"Result: Success")
      case Failure(t) => t.printStackTrace()
    }
  }

  def listOfTableElements(listener: MyListener) = {

    val f: Future[Seq[ClientDataModel]] = db.run(clients.result)

    f
  }

}
