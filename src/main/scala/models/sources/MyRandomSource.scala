package models.sources
import models.matrices.ImageMatrix
import models.pixels.ColorPixel

class MyRandomSource extends RandomSource{
  override def getSource(): ImageMatrix[ColorPixel] = ???
}
