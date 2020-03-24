package hftn.service.sample.server.web.v1

import com.google.inject.Inject
import hftn.service.core.client.ClientError
import hftn.service.core.model.identifiers.DeckId
import hftn.service.sample.model.DeckService
import hftn.service.sample.server.web.v1.ResourceMapper.{toClientDeck, toClientDecks}
import play.api.Logger
import play.api.data.Form
import play.api.libs.json.Json
import play.api.mvc._

case class DeckFormInput(id: Int, name: String)


class DeckController @Inject()(val controllerComponents: ControllerComponents,
                               val service: DeckService) extends BaseController {

  private val logger = Logger(getClass)

  private val form: Form[DeckFormInput] = {
    import play.api.data.Forms._

    Form(
      mapping(
        "id" -> number,
        "name" -> nonEmptyText
      )(DeckFormInput.apply)(DeckFormInput.unapply)
    )
  }


  def get(id: Int) = Action { implicit request: Request[AnyContent] =>
    logger.trace(s"Retrieves deck which id is ${id}")
    service.get(DeckId(id)) match {
      case Some(s) => Ok(Json.toJson(toClientDeck(s)))
      case None => NotFound(Json.toJson(ClientError(s"Deck Not Found for id : ${id}")))
    }
  }

  def post() = Action { implicit request: Request[AnyContent] =>
    logger.trace("posts new Deck")
    processJsonPost()
  }

  def getDecks() = Action { implicit request: Request[AnyContent] =>
    logger.trace(s"Retrieves all decks")
    Ok(Json.toJson(toClientDecks(service.getList())))
  }


  private def processJsonPost[A]()(
    implicit request: Request[A]): Result = {
    def failure(badForm: Form[DeckFormInput]) = {
      BadRequest(Json.toJson(ClientError("Malformed input Deck")))
    }

    def success(input: DeckFormInput) = {
      Created(Json.toJson(service.add(input).underlying))
    }

    form.bindFromRequest().fold(failure, success)
  }
}
