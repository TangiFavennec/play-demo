package hftn.service.sample.model

import hftn.service.core.model.identifiers.{CardMappingId, DeckId}
import hftn.service.sample.server.web.v1.CardMappingFormInput
import play.api.MarkerContext

trait CardMappingService {

  def add(data: CardMappingFormInput)(implicit mc: MarkerContext): CardMappingId

  def get(id: CardMappingId)(implicit mc: MarkerContext): Option[CardMapping]

  def getList(deckId : DeckId)(implicit mc: MarkerContext): Iterable[CardMapping]

}
