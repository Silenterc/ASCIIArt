package converters

import models.matrices.ImageMatrix
import models.pixels.{CharPixel, ColorPixel, GreyscalePixel}
import models.tables.LinearTable

class ToAsciiLinearConverter(tab: LinearTable = new LinearTable()) extends ToAsciiConverter {
  private var table: LinearTable = tab
  override def convert(imageMatrix: ImageMatrix[GreyscalePixel]): ImageMatrix[CharPixel] = {
    imageMatrix.mapEvery {
      greyscalePixel =>
        CharPixel(table.getChar(greyscalePixel.value))
    }
  }

  def setTable(tab: LinearTable): Unit = {
    table = tab
  }
}
