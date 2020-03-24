package hftn.service.sample.persistence

import hftn.service.core.model.identifiers.{CardMappingId, DeckId}
import hftn.service.sample.model.CardMapping
import hftn.service.sample.server.web.v1.CardMappingFormInput
import play.api.MarkerContext

trait CardMappingRepository {
  def add(data: CardMappingFormInput)(implicit mc: MarkerContext): CardMappingId

  def get(id: CardMappingId)(implicit mc: MarkerContext): Option[CardMapping]

  def getList(deckId : DeckId)(implicit mc: MarkerContext): Iterable[CardMapping]

}
