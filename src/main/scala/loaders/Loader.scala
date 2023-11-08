package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.ImageSource

trait Loader[T <: ImageSource[_]] {
  def load(source: T): ImageMatrix[ColorPixel]
}
