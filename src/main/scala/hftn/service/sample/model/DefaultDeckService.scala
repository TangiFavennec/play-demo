package hftn.service.sample.model
import com.google.inject.Inject
import hftn.service.core.model.identifiers.DeckId
import hftn.service.sample.persistence.DeckRepository
import hftn.service.sample.server.web.v1.DeckFormInput
import play.api.MarkerContext

import scala.concurrent.Future

class DefaultDeckService @Inject()(deckRepository: DeckRepository) extends DeckService {

  override def add(data: DeckFormInput)(implicit mc: MarkerContext): DeckId = {
    deckRepository.add(data)
  }

  override def get(id: DeckId)(implicit mc: MarkerContext): Option[Deck] = {
    deckRepository.get(id)
  }

  override def getList()(implicit mc: MarkerContext): Iterable[Deck] = {
    deckRepository.getList()
  }
}
