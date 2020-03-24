package hftn.service.sample

import com.google.inject.AbstractModule
import hftn.service.sample.model.{CardMappingService, DeckService, DefaultCarMappingService, DefaultDeckService}
import hftn.service.sample.persistence.inmemory.{InMemoryCardMappingRepository, InMemoryDeckRepository}
import hftn.service.sample.persistence.{CardMappingRepository, DeckRepository}
import javax.inject.Singleton
import net.codingwell.scalaguice.ScalaModule
import play.api.{Configuration, Environment}
;

/**
 * Sets up custom components for Play.
 *
 * https://www.playframework.com/documentation/latest/ScalaDependencyInjection
 */
class Module(environment: Environment, configuration: Configuration)
  extends AbstractModule
    with ScalaModule {

  override def configure() = {
    bind[CardMappingRepository].to[InMemoryCardMappingRepository].in[Singleton]
    bind[DeckRepository].to[InMemoryDeckRepository].in[Singleton]
    bind[DeckService].to[DefaultDeckService]
    bind[CardMappingService].to[DefaultCarMappingService]
  }
}
