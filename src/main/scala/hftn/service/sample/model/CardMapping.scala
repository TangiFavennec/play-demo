package hftn.service.sample.model

import hftn.service.core.model.identifiers.{CardMappingId, DeckId}

case class CardMapping(val id : CardMappingId,
                       val deckId : DeckId,
                       val expr : String,
                       val translations : Iterable[String] = List()) {

}
