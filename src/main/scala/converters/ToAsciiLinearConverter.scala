package converters

import models.LinearTable
import models.matrices.ImageMatrix
import models.pixels.{CharPixel, ColorPixel, GreyscalePixel}

class ToAsciiLinearConverter(tab: LinearTable = new LinearTable()) extends Converter[ImageMatrix[GreyscalePixel], ImageMatrix[CharPixel]] {
  private var table: LinearTable = tab
  override def convert(imageMatrix: ImageMatrix[GreyscalePixel]): ImageMatrix[CharPixel] = {
    imageMatrix.map {
      greyscalePixel =>
        CharPixel(table.getChar(greyscalePixel.value))
    }
  }

  def setTable(tab: LinearTable): Unit = {
    table = tab
  }
}
