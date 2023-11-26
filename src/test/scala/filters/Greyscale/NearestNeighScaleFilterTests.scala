package filters.Greyscale

import filters.Greyscale.NearestNeighScaleFilter
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class NearestNeighScaleFilterTests extends FunSuite {
  test("apply with scale 1 should keep the matrix identical") {
    val scale = 1.0
    val tested = new NearestNeighScaleFilter(scale)

    val inputMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(1), GreyscalePixel(2)),
                                          List(GreyscalePixel(3), GreyscalePixel(4))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    assert(resultMatrix.getMatrix equals inputMatrix.getMatrix)
  }

  test("apply with scale 0.25 should transform 2x2 matrix into 1x1 with the top left element") {
    val scale = 0.25
    val tested = new NearestNeighScaleFilter(scale)

    val inputMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel('A'), GreyscalePixel('B')),
                                          List(GreyscalePixel('C'), GreyscalePixel('D'))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
      List(GreyscalePixel('A')),
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }

  test("apply with scale 0.25 should transform 4x4 matrix into 2x2 with ABCD") {
    val scale = 0.25
    val tested = new NearestNeighScaleFilter(scale)

    val inputList = List(
                        List(GreyscalePixel('A'), GreyscalePixel('A'), GreyscalePixel('B'), GreyscalePixel('B')),
                        List(GreyscalePixel('A'), GreyscalePixel('A'), GreyscalePixel('B'), GreyscalePixel('B')),
                        List(GreyscalePixel('C'), GreyscalePixel('C'), GreyscalePixel('D'), GreyscalePixel('D')),
                        List(GreyscalePixel('C'), GreyscalePixel('C'), GreyscalePixel('D'), GreyscalePixel('D'))
    )
    val resultMatrix = tested.apply(new ImageMatrix[GreyscalePixel](inputList))

    val expectedMatrix = new ImageMatrix(List(
                                            List(GreyscalePixel('A'), GreyscalePixel('B')),
                                            List(GreyscalePixel('C'), GreyscalePixel('D'))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }

}
