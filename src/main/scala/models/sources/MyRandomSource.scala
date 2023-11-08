package models.sources
import models.matrices.ImageMatrix
import models.pixels.RGBPixel

class MyRandomSource extends RandomSource{
  override def getSource(): ImageMatrix[RGBPixel] = ???
}
