package converters

import models.matrices.ImageMatrix
import models.pixels.{ColorPixel, GreyscalePixel}

class ToGrayscaleConverter extends Converter[ImageMatrix[ColorPixel], ImageMatrix[GreyscalePixel]] {
  override def convert(imageMatrix: ImageMatrix[ColorPixel]): ImageMatrix[GreyscalePixel] = {
    imageMatrix.map{
      colorPixel =>
        val greyscaleValue = (0.3 * colorPixel.red) + (0.59 * colorPixel.green) + (0.11 * colorPixel.blue)
        GreyscalePixel(greyscaleValue)
    }




 }
}
