package Controller

import Model.ClientData
import net.liftweb.json.Serialization.write
import net.liftweb.json.{DefaultFormats, JsonAST, JsonDSL}


object JsonHandler {

  def createJson(seq: Seq[ClientData]): String = {
    implicit val formats = DefaultFormats
    val jsonString = write(seq)
    jsonString
  }

}
