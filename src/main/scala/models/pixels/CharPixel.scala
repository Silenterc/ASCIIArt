package models.pixels


case class CharPixel(initial: Char) extends Pixel[Char]{
    value = initial

    override def toString: String = {
        value.toString
    }
}
