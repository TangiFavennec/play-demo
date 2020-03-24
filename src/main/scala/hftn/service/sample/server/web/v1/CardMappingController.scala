package hftn.service.sample.server.web.v1

import hftn.service.core.client.ClientError
import hftn.service.core.model.identifiers.{CardMappingId, DeckId}
import hftn.service.sample.model.CardMappingService
import hftn.service.sample.server.web.v1.ResourceMapper.{toClientCardMapping, toClientCardMappings}
import javax.inject.{Inject, Singleton}
import play.api.Logger
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

case class CardMappingFormInput(id: Int, deckId: Int, expr: String)


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class CardMappingController @Inject()(val controllerComponents: ControllerComponents,
                                      val service: CardMappingService)
                                     (implicit ec: ExecutionContext) extends BaseController {

  private val logger = Logger(getClass)

  private val form: Form[CardMappingFormInput] = {
    import play.api.data.Forms._

    Form(
      mapping(
        "id" -> number,
        "deck_id" -> number,
        "name" -> nonEmptyText
      )(CardMappingFormInput.apply)(CardMappingFormInput.unapply)
    )
  }

  def get(id: Int) = Action { implicit request: Request[AnyContent] =>
    logger.trace(s"One Mapping which id is ${id}")
    service.get(CardMappingId(id)) match {
      case Some(s) => Ok(Json.toJson(toClientCardMapping(s)))
      case None => NotFound(Json.toJson(ClientError(s"Card Mapping Not Found for id : ${id}")))
    }
  }

  def post() = Action { implicit request: Request[AnyContent] =>
    logger.trace("posts cardMapping")
    processJsonPost()

  }

  def getList(deckId: Int) = Action { implicit request: Request[AnyContent] =>
    logger.trace(s"Retrieves all cardMappings in deck which id is : ${deckId}")
    Ok(Json.toJson(toClientCardMappings(service.getList(DeckId(deckId)))))
  }

  private def processJsonPost[A]()(
    implicit request: Request[A]): Result = {
    def failure(badForm: Form[CardMappingFormInput]) = {
      BadRequest(Json.toJson(ClientError("Malformed input Card Mapping")))
    }

    def success(input: CardMappingFormInput) = {
      Created(Json.toJson(service.add(input).underlying))
    }

    form.bindFromRequest().fold(failure, success)
  }
}