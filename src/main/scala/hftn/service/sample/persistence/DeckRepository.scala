package hftn.service.sample.persistence

import hftn.service.core.model.identifiers.DeckId
import hftn.service.sample.model.Deck
import hftn.service.sample.server.web.v1.DeckFormInput
import play.api.MarkerContext

trait DeckRepository {

  def add(data: DeckFormInput)(implicit mc: MarkerContext): DeckId

  def get(id: DeckId)(implicit mc: MarkerContext): Option[Deck]

  def getList()(implicit mc: MarkerContext): Iterable[Deck]
}
