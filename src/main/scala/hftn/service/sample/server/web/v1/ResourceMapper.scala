package hftn.service.sample.server.web.v1

import hftn.service.core.model.identifiers.{CardMappingId, DeckId}
import hftn.service.sample.client.{ClientCardMapping, ClientDeck}
import hftn.service.sample.model.{CardMapping, Deck}

object ResourceMapper {

  def fromClientDeck(input: ClientDeck): Deck = {
    Deck(DeckId(input.id), input.name, input.details, input.kanjis.map(fromClientCardMapping))
  }

  def fromClientCardMapping(cm: ClientCardMapping): CardMapping = {
    CardMapping(CardMappingId(cm.id), DeckId(cm.deckId), cm.expr, cm.translations)
  }

  def toClientDeck(input: Deck): ClientDeck = {
    ClientDeck(input.id.underlying, input.name, input.details, input.mappings.map(toClientCardMapping))
  }

  def toClientDecks(input: Iterable[Deck]): Iterable[ClientDeck] = {
    input.map(toClientDeck)
  }

  def toClientCardMapping(cm: CardMapping): ClientCardMapping = {
    ClientCardMapping(cm.id.underlying, cm.deckId.underlying, cm.expr, cm.translations)
  }

  def toClientCardMappings(cm: Iterable[CardMapping]): Iterable[ClientCardMapping] = {
    cm.map(toClientCardMapping)
  }

}
