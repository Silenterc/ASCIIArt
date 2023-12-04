package converters

import models.matrices.ImageMatrix
import models.pixels.{ColorPixel, GreyscalePixel}
/**
 * Converter of a ColorPixel Matrix to a GreyscalePixel Matrix
 */
class ToGreyscaleConverter extends Converter[ImageMatrix[ColorPixel], ImageMatrix[GreyscalePixel]] {
  /**
   * Converts a ColorPixel Matrix to a GreyscalePixel Matrix
   *
   * @param imageMatrix to be converted Matrix
   * @return converted Matrix
   */
  override def convert(imageMatrix: ImageMatrix[ColorPixel]): ImageMatrix[GreyscalePixel] = {
    imageMatrix.mapEvery{
      colorPixel =>
        val greyscaleValue = (0.3 * colorPixel.red) + (0.59 * colorPixel.green) + (0.11 * colorPixel.blue)
        GreyscalePixel(greyscaleValue)
    }




 }
}
