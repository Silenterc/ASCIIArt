package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.{ImageSource, MyRandomSource, RandomSource}
/**
 * Loader of an ImageMatrix from some My Random Source
 */
class MyRandomImageLoader(source: MyRandomSource) extends RandomImageLoader {
  /**
   * Loads a random ImageMatrix from the random source
   * @return random ImageMatrix
   */
  override def load(): ImageMatrix[ColorPixel] = {
    source.getSource
  }

}
