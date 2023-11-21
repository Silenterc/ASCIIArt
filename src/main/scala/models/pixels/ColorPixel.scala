package models.pixels

import java.awt.Color

case class ColorPixel(initial: Color) extends Pixel[Color]{
  value = initial
  def red: Int = value.getRed
  def green: Int = value.getGreen
  def blue: Int = value.getBlue
}
