package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.{ImageSource, MyRandomSource, RandomSource}

class MyRandomImageLoader(source: MyRandomSource) extends RandomImageLoader {
  override def load(): ImageMatrix[ColorPixel] = {
    source.getSource()
  }

}
