package hftn.service.sample.persistence.inmemory

import com.google.inject.Inject
import hftn.service.core.model.identifiers.DeckId
import hftn.service.sample.model.Deck
import hftn.service.sample.persistence.DeckRepository
import hftn.service.sample.server.web.v1.DeckFormInput
import play.api.{Logger, MarkerContext}

import scala.concurrent.ExecutionContext

class InMemoryDeckRepository @Inject()()(implicit ec: ExecutionContext) extends DeckRepository {


  private val logger = Logger(this.getClass)

  private var deckList = List(
    Deck(DeckId(1), "deck 1", "details 1"),
    Deck(DeckId(2), "deck 2", "details 2"),
    Deck(DeckId(3), "deck 3", "details 3"),
    Deck(DeckId(4), "deck 4", "details 4"),
    Deck(DeckId(5), "deck 5", "details 5")
  )

  override def add(data: DeckFormInput)(implicit mc: MarkerContext): DeckId = {
    DeckId(1)
  }

  override def get(id: DeckId)(implicit mc: MarkerContext): Option[Deck] = {
    logger.trace(s"get: id = $id")
    deckList.find(post => post.id == id)
  }

  override def getList()(implicit mc: MarkerContext): Iterable[Deck] = {
    logger.trace(s"Retrieved all decks : ${deckList}")
    deckList
  }
}
