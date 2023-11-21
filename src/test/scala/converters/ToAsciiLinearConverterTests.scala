package converters

import models.matrices.ImageMatrix
import models.{LinearTable, pixels}
import models.pixels.{CharPixel, GreyscalePixel}
import org.scalatest.FunSuite

import java.awt.Color

class ToAsciiLinearConverterTests extends FunSuite{
  test("Convert an existing 2x3 ImageMatrix[GreyscalePixel] using a \".|\" table") {
    val matrix = List(List(GreyscalePixel(0), pixels.GreyscalePixel(50)),
      List(pixels.GreyscalePixel(250), pixels.GreyscalePixel(70)),
      List(pixels.GreyscalePixel(128), pixels.GreyscalePixel(255)))
    val input = new ImageMatrix(matrix)
    val inputTable = new LinearTable(".|")
    val tested = new ToAsciiLinearConverter(inputTable)

    val result = tested.convert(input)

    assert(result.matrix.size == 3)
    assert(result.matrix.forall(list => list.size == 2))

    val correctMatrix = List(List(CharPixel('.'), CharPixel('.')),
                             List(CharPixel('|'), CharPixel('.')),
                             List(CharPixel('|'), CharPixel('|')))
    assert(result.matrix equals correctMatrix)
  }
}
