package filters.Greyscale

import filters.Greyscale.InvertFilter
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class InvertFilterTests extends FunSuite{
  test("apply should correctly invert greyscale values in a 2x2 matrix") {
    val tested = new InvertFilter

    val inputMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100)),
      List(GreyscalePixel(150), GreyscalePixel(200))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
      List(GreyscalePixel(205), GreyscalePixel(155)),
      List(GreyscalePixel(105), GreyscalePixel(55))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }

  test("apply should handle an empty input matrix") {
    val tested = new InvertFilter

    val inputMatrix = new ImageMatrix(List.empty[List[GreyscalePixel]])

    val resultMatrix = tested.apply(inputMatrix)

    assert(resultMatrix.getMatrix.isEmpty)
  }

  test("apply should correctly handle a single-row input matrix") {
    val tested = new InvertFilter

    val inputMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100), GreyscalePixel(150))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
      List(GreyscalePixel(205), GreyscalePixel(155), GreyscalePixel(105))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }

  test("apply should correctly handle edge values in input matrix") {
    val tested = new InvertFilter

    val inputMatrix = new ImageMatrix(List(
      List(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(255))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
      List(GreyscalePixel(0), GreyscalePixel(255), GreyscalePixel(0))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }
}
