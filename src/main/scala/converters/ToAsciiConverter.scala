package converters

import models.matrices.ImageMatrix
import models.pixels.{CharPixel, GreyscalePixel}
import models.tables.Table

class ToAsciiConverter(table: Table) extends Converter[ImageMatrix[GreyscalePixel], ImageMatrix[CharPixel]] {
  override def convert(imageMatrix: ImageMatrix[GreyscalePixel]): ImageMatrix[CharPixel] = {
    imageMatrix.mapEvery {
      greyscalePixel =>
        CharPixel(table.getChar(greyscalePixel.value))
    }
  }
}
