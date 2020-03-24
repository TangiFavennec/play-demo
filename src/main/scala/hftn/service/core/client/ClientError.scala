package hftn.service.core.client

import play.api.libs.json.{Format, Json}


case class ClientError(message: String, details: String) {
}

object ClientError {
  /**
   * Mapping to read/write a ClientCardMapping out as a JSON value.
   */
  implicit val format: Format[ClientError] = Json.format

  def apply(message: String): ClientError = apply(message, "")
}


