package Model


import scala.language.higherKinds

import slick.jdbc.PostgresProfile.api._

class Clients(tag: Tag) extends Table[ClientDataModel](tag, "CLIENTS") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("FIRST_NAME")
  def lastName = column[String]("LAST_NAME")
  def birthDate = column[String]("BIRTH_DATE")
  def address = column[String]("ADDRESS")

  def * = (id, firstName, lastName, birthDate, address).mapTo[ClientDataModel]
}