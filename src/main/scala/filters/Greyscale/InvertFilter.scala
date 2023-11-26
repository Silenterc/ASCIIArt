package filters.Greyscale
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel

class InvertFilter(val whiteValue:Int = 255) extends GreyscaleFilter {
  override def apply(matrix: ImageMatrix[GreyscalePixel]): ImageMatrix[GreyscalePixel] = {
    matrix.mapEvery(pix => GreyscalePixel(whiteValue - pix.value))
  }
}
