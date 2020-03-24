package hftn.service.sample.client

import play.api.libs.json.{Format, Json}

case class ClientDeck(val id : Int, val name : String, val details: String, val kanjis: Iterable[ClientCardMapping] = List()) {

}

object ClientDeck {
  /**
   * Mapping to read/write a ClientDeck out as a JSON value.
   */
  implicit val format: Format[ClientDeck] = Json.format
}
