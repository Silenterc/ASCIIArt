package converters

import models.matrices.ImageMatrix
import models.pixels.{CharPixel, GreyscalePixel}
import models.tables.Table

/**
 * Converter of a GreyscalePixel Matrix to an Ascii/CharPixel Matrix
 * @param table Conversion table - Linear/Non linear...
 */
class ToAsciiConverter(table: Table) extends Converter[ImageMatrix[GreyscalePixel], ImageMatrix[CharPixel]] {
  /**
   *  Converts a GreyscalePixel Matrix to an Ascii/CharPixel Matrix
   *  @param imageMatrix to be converted Matrix
   *  @return converted Matrix using the table
   */
  override def convert(imageMatrix: ImageMatrix[GreyscalePixel]): ImageMatrix[CharPixel] = {
    imageMatrix.mapEvery {
      greyscalePixel =>
        CharPixel(table.getChar(greyscalePixel.value))
    }
  }
}
