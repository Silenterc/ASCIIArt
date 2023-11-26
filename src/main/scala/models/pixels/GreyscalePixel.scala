package models.pixels
case class GreyscalePixel(initial: Double) extends Pixel[Double]{
  value = initial

  override def toString: String = {
    value.toInt.toString
  }

}
