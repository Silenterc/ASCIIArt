package models.pixels
/**
 * Pixel of Greyscale value
 * @param initial initial greyscale value
 */
case class GreyscalePixel(initial: Double) extends Pixel[Double]{
  value = initial

  override def toString: String = {
    value.toInt.toString
  }

}
