package loaders

import models.matrices.ImageMatrix
import models.pixels.RGBPixel
import models.sources.ImageSource

trait Loader[T <: ImageSource[_]] {
  def load(source: T): ImageMatrix[RGBPixel]
}
