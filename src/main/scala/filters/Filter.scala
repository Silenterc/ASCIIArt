package filters

import models.matrices.ImageMatrix
import models.pixels.Pixel

/**
 * Filters some ImageMatrix with some Pixels
 * @tparam T The type of Pixel
 */
trait Filter[T <: Pixel[_]] {
  /**
   * Apply the filter
   * @param matrix matrix which will have the filter applied on it
   * @return The matrix with the filter applied
   */
  def apply(matrix: ImageMatrix[T]): ImageMatrix[T]
}
