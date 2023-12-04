package filters.Greyscale
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel
/**
 * Filter which inverts the greyscale value of pixels
 */
class InvertFilter(val whiteValue:Int = 255) extends GreyscaleFilter {
  /**
   * Applies the Inversion
   *  @param matrix matrix which will have the filter applied on it
   *  @return The matrix with the filter applied
   */
  override def apply(matrix: ImageMatrix[GreyscalePixel]): ImageMatrix[GreyscalePixel] = {
    matrix.mapEvery(pix => GreyscalePixel(whiteValue - pix.value))
  }
}
