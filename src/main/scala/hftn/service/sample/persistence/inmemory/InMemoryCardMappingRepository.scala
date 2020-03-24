package hftn.service.sample.persistence.inmemory

import com.google.inject.Inject
import hftn.service.core.model.identifiers.{CardMappingId, DeckId}
import hftn.service.sample.model.CardMapping
import hftn.service.sample.persistence.CardMappingRepository
import hftn.service.sample.server.web.v1.CardMappingFormInput
import play.api.{Logger, MarkerContext}

import scala.concurrent.ExecutionContext

class InMemoryCardMappingRepository @Inject()()(implicit ec: ExecutionContext) extends CardMappingRepository {

  private val logger = Logger(this.getClass)

  private var cardMappingsList = List(
    CardMapping(CardMappingId(1), DeckId(1), "あ"),
    CardMapping(CardMappingId(2), DeckId(1), "い"),
    CardMapping(CardMappingId(3), DeckId(1), "う"),
    CardMapping(CardMappingId(4), DeckId(1), "え"),
    CardMapping(CardMappingId(5), DeckId(1), "お")
  )

  override def add(data: CardMappingFormInput)(implicit mc: MarkerContext) : CardMappingId = {
      CardMappingId(1)
  }

  override def get(id: CardMappingId)(implicit mc: MarkerContext): Option[CardMapping] = {
      logger.trace(s"get: id = $id")
      cardMappingsList.find(post => post.id == id)
  }

  override def getList(deckId : DeckId)(implicit mc: MarkerContext): Iterable[CardMapping] = {
      logger.trace(s"Retrieved all kanjis : ${cardMappingsList}")
      cardMappingsList.filter(c => c.deckId == deckId)
  }
}
