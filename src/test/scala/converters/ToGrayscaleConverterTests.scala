package converters

import models.matrices.ImageMatrix
import models.pixels.{ColorPixel, GreyscalePixel}
import org.scalatest.FunSuite

import java.awt.Color

class ToGrayscaleConverterTests extends FunSuite{

  test("Convert an existing 2x3 ImageMatrix[ColorPixel]") {
    val matrix = List(List(ColorPixel(Color.cyan), ColorPixel(Color.black)),
                      List(ColorPixel(Color.red), ColorPixel(Color.pink)),
                      List(ColorPixel(Color.white), ColorPixel(Color.yellow)))
    val input = new ImageMatrix(matrix)
    val tested = new ToGrayscaleConverter
    val result = tested.convert(input)
    assert(result.size == 3)
    assert(result.forall(list => list.size == 2))

    val correctMatrix = List(List(GreyscalePixel(Color.cyan.getRed * 0.3 + Color.cyan.getGreen * 0.59 + Color.cyan.getBlue * 0.11),
                                  GreyscalePixel(Color.black.getRed * 0.3 + Color.black.getGreen * 0.59 + Color.black.getBlue * 0.11)),
                             List(GreyscalePixel(Color.red.getRed * 0.3 + Color.red.getGreen * 0.59 + Color.red.getBlue * 0.11),
                                  GreyscalePixel(Color.pink.getRed * 0.3 + Color.pink.getGreen * 0.59 + Color.pink.getBlue * 0.11)),
                             List(GreyscalePixel(Color.white.getRed * 0.3 + Color.white.getGreen * 0.59 + Color.white.getBlue * 0.11),
                                  GreyscalePixel(Color.yellow.getRed * 0.3 + Color.yellow.getGreen * 0.59 + Color.yellow.getBlue * 0.11)))
    assert(result.getMatrix equals correctMatrix)

  }
}
