package hftn.service.core.model.identifiers

case class CardMappingId(val underlying: Int) extends AnyVal {
  override def toString: String = underlying.toString
}
