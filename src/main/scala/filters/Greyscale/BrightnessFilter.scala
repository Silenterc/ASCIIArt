package filters.Greyscale
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel

/**
 * Filter which changes the brightness of pixels by change
 * @param change change
 */
class BrightnessFilter(val change: Int) extends GreyscaleFilter {
  /**
   * Applies the Brightness Filter within character bounds (0-255)
   * @param matrix matrix which will have the filter applied on it
   * @return The matrix with the filter applied
   */
  override def apply(matrix: ImageMatrix[GreyscalePixel]): ImageMatrix[GreyscalePixel] = {
    matrix.mapEvery(pix => {
      var newValue = pix.value + change
      if (newValue < 0) newValue = 0
      else if (newValue > 255) newValue = 255
      GreyscalePixel(newValue)
    })

  }
}
