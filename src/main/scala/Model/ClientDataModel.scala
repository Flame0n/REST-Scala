package Model

case class ClientDataModel(id: Long = 0, firstName: String, lastName: String, birthDate: String, address: String) {
  val this.id = id
  val this.firstName = firstName
  val this.lastName = lastName
  val this.birthDate = birthDate
  val this.address = address

  override def toString: String = id + " " + firstName + " " + lastName + " " + birthDate + " " + address + " "


}

