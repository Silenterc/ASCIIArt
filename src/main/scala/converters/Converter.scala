package converters

import models.matrices.ImageMatrix
import models.pixels.Pixel

/**
 * Converter of an ImageMatrix of some Pixel to an ImageMatrix of some other Pixel
 * @tparam F Original Matrix
 * @tparam T Converted Matrix
 */
trait Converter [F <: ImageMatrix[_ <: Pixel[_]], T <: ImageMatrix[_ <: Pixel[_]]]{
  /**
   * Converts an ImageMatrix of some Pixel to an ImageMatrix of some other Pixel
   * @param imageMatrix to be converted Matrix
   * @return converted Matrix
   */
  def convert(imageMatrix: F): T
}
