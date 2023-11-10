package models.pixels

import java.awt.Color

case class ColorPixel(initial: Color) extends Pixel[Color]{
  value = initial
}
