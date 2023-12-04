package models.pixels

import java.awt.Color
/**
 * Pixel of Color value
 * @param initial initial Color value
 */
case class ColorPixel(initial: Color) extends Pixel[Color]{
  value = initial
  def red: Int = value.getRed
  def green: Int = value.getGreen
  def blue: Int = value.getBlue

  override def toString: String = {
    s"[$red,$green,$blue]"
  }
}
