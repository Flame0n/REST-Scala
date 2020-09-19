package Model

import java.sql.Blob

import akka.actor.Address
import javax.sql.rowset.serial.SerialBlob
import org.reactivestreams.Publisher

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import slick.basic.DatabasePublisher
import slick.jdbc.PostgresProfile.api._


class DatabaseManager {

  val user = "postgres"
  val url = "jdbc:postgresql://localhost:5432/1"
  val password = "1234"
  val driver = "org.postgresql.Driver"

  lazy val db = Database.forURL(url, user = user, password = password, driver = driver)

  val clients = TableQuery[Clients]

  def incert(client: ClientDataModel): Unit = {
    val userId = (clients returning clients.map(_.id)) += client
    val f = db.run(userId)
    f.onComplete {
      case Success(s) => println(s"Result: $s")
      case Failure(t) => t.printStackTrace()
    }
  }

  def deleteById(id: Long) = {
    val action = for{
      c <- clients
      if(c.id === id)
    } yield c
    db.run(action.result)
  }

  def deleteByFirstName(firstName: String) = {
    val action = clients.filter(_.firstName === firstName).delete
    db.run(action)
  }

  def deleteByLastName(lastName: String) = {
    val action = clients.filter(_.lastName === lastName).delete
    db.run(action)
  }

  def deleteByBirthDate(lastName: String) = {
    val action = clients.filter(_.lastName === lastName).delete
    db.run(action)
  }

  def searchByID(id: Long): Unit = {
    val action = clients.filter(_.id === id).result
    db.run(action)
  }

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


}
