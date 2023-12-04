package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.ImageSource

/**
 * Loader of an ImageMatrix
 */
trait Loader {
  /**
   * Loads an ImageMatrix from somewhere
   */
  def load(): ImageMatrix[ColorPixel]
}
