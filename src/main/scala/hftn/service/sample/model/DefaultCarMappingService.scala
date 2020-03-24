package hftn.service.sample.model
import com.google.inject.Inject
import hftn.service.core.model.identifiers.{CardMappingId, DeckId}
import hftn.service.sample.persistence.CardMappingRepository
import hftn.service.sample.server.web.v1.CardMappingFormInput
import play.api.MarkerContext

class DefaultCarMappingService @Inject() (cardMappingService: CardMappingRepository) extends CardMappingService {

  override def add(data: CardMappingFormInput)(implicit mc: MarkerContext): CardMappingId = {
    cardMappingService.add(data)
  }

  override def get(id: CardMappingId)(implicit mc: MarkerContext): Option[CardMapping] = {
    cardMappingService.get(id)
  }

  override def getList(deckId: DeckId)(implicit mc: MarkerContext): Iterable[CardMapping] = {
    cardMappingService.getList(deckId)
  }
}
