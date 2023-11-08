package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.{ImageSource, MyRandomSource, RandomSource}

class MyRandomImageLoader extends RandomImageLoader[MyRandomSource] {
  override def load(source: MyRandomSource): ImageMatrix[ColorPixel] = ???

}
