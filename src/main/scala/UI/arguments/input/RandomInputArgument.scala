package UI.arguments.input

import loaders.{Loader, MyRandomImageLoader, RandomImageLoader}
import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.MyRandomSource

class RandomInputArgument extends InputArgument {
  override def getResult: Loader = {
    new MyRandomImageLoader(new MyRandomSource)
  }
}
