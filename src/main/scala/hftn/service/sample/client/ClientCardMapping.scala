package hftn.service.sample.client

import play.api.libs.json.{Format, Json}

case class ClientCardMapping(val id : Int,
                             val deckId : Int,
                             val expr : String,
                             val translations : Iterable[String] = List()) {

}

object ClientCardMapping {
  /**
   * Mapping to read/write a ClientCardMapping out as a JSON value.
   */
  implicit val format: Format[ClientCardMapping] = Json.format
}


