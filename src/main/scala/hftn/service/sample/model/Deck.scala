package hftn.service.sample.model

import hftn.service.core.model.identifiers.DeckId

case class Deck(val id : DeckId, val name : String, val details: String, val mappings: Iterable[CardMapping] = List()) {

}
