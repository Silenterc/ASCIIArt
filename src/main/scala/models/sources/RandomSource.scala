package models.sources

import models.matrices.ImageMatrix
import models.pixels.ColorPixel

trait RandomSource extends ImageSource[ImageMatrix[ColorPixel]]{
  val maxHeight = 500
  val maxWidth = 500


}
