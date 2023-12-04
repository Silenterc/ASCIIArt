package models.pixels

/**
 * Pixel of Char/ASCII value
 * @param initial initial Char value
 */
case class CharPixel(initial: Char) extends Pixel[Char]{
    value = initial

    override def toString: String = {
        value.toString
    }
}
