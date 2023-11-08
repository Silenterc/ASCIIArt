package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.JPEGFileSource

class JPEGImageLoader extends FileImageLoader[JPEGFileSource] {
  override def load(source: JPEGFileSource): ImageMatrix[ColorPixel] = ???
}
