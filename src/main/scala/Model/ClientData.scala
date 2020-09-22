package Model

case class ClientData(id: Long = 0, firstName: String, lastName: String, birthDate: String, address: String) {
  override def toString: String = id + " " + firstName + " " + lastName + " " + birthDate + " " + address + " "
}



