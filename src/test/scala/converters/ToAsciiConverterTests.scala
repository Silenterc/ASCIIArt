package converters

import models.matrices.ImageMatrix
import models.pixels
import models.pixels.{CharPixel, GreyscalePixel}
import models.tables.LinearTable
import org.scalatest.FunSuite

class ToAsciiConverterTests extends FunSuite {
  test("Convert an existing 2x3 ImageMatrix[GreyscalePixel] using a linear \".|\" table") {
    val matrix = List(List(GreyscalePixel(0), pixels.GreyscalePixel(50)),
                      List(pixels.GreyscalePixel(250), pixels.GreyscalePixel(70)),
                      List(pixels.GreyscalePixel(128), pixels.GreyscalePixel(255)))
    val input = new ImageMatrix(matrix)
    val inputTable = new LinearTable(".|")
    val tested = new ToAsciiConverter(inputTable)

    val result = tested.convert(input)

    Predef.assert(result.size() == 3)
    assert(result.forall(list => list.size == 2))

    val correctMatrix = List(List(CharPixel('.'), CharPixel('.')),
      List(CharPixel('|'), CharPixel('.')),
      List(CharPixel('|'), CharPixel('|')))
    assert(result.getMatrix equals correctMatrix)
  }

  test("convert an existing 2x2 Matrix using a linear \"?%?%!._\" table with edge values" ) {
    // 256/7 = 36.57, so edge values will be multiplicities of it rounded up/down
    val input = new ImageMatrix(List(
                                    List(GreyscalePixel(36), GreyscalePixel(37)),
                                    List(GreyscalePixel(182), GreyscalePixel(183)),
                                    List(GreyscalePixel(109), GreyscalePixel(110)),
                                    List(GreyscalePixel(219), GreyscalePixel(255)))
    )
    val inputTable = new LinearTable("?%?%!._")
    val tested = new ToAsciiConverter(inputTable)

    val result = tested.convert(input)
    val correctMatrix = new ImageMatrix(List(
                                            List(CharPixel('?'), CharPixel('%')),
                                            List(CharPixel('!'), CharPixel('.')),
                                            List(CharPixel('?'), CharPixel('%')),
                                            List(CharPixel('.'), CharPixel('_'))
    ))

    assert(result.getMatrix equals correctMatrix.getMatrix)
  }

  test("convert an empty input matrix") {
    val inputTable = new LinearTable("?%?%!._")
    val tested = new ToAsciiConverter(inputTable)

    val inputMatrix = new ImageMatrix(List.empty[List[GreyscalePixel]])

    val resultMatrix = tested.convert(inputMatrix)

    assert(resultMatrix.getMatrix.isEmpty)
  }

}
