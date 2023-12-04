package models.sources

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
/**
 * Some Random source of Image
 */
trait RandomSource extends ImageSource[ImageMatrix[ColorPixel]]{
  /**
   * These values are here so the randomly generated image isn't too big
   */
  val maxHeight = 500
  val maxWidth = 500


}
