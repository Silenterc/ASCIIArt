package filters.Greyscale
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel

class BrightnessFilter(val change: Int) extends GreyscaleFilter {
  override def apply(matrix: ImageMatrix[GreyscalePixel]): ImageMatrix[GreyscalePixel] = {
    matrix.mapEvery(pix => {
      var newValue = pix.value + change
      if (newValue < 0) newValue = 0
      else if (newValue > 255) newValue = 255
      GreyscalePixel(newValue)
    })

  }
}
