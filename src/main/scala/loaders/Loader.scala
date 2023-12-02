package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.ImageSource

trait Loader {
  def load(): ImageMatrix[ColorPixel]
}
