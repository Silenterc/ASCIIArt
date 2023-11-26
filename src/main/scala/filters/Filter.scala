package filters

import models.matrices.ImageMatrix
import models.pixels.Pixel

trait Filter[T <: Pixel[_]] {
  def apply(matrix: ImageMatrix[T]): ImageMatrix[T]
}
