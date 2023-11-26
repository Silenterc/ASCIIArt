package filters.Greyscale

import filters.Greyscale.BrightnessFilter
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel
import org.scalatest.FunSuite

class BrightnessFilterTests extends FunSuite {
  test("apply should change brightness by the specified positive amount - no edge cases") {
    val change = 30
    val tested = new BrightnessFilter(change)

    val inputMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(50), GreyscalePixel(100)),
                                          List(GreyscalePixel(150), GreyscalePixel(200))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(80), GreyscalePixel(130)),
                                          List(GreyscalePixel(180), GreyscalePixel(230))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }

  test("apply should change brightness by the specified negative amount - no edge cases") {
    val change = -30
    val tested = new BrightnessFilter(change)

    val inputMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(50), GreyscalePixel(100)),
                                          List(GreyscalePixel(150), GreyscalePixel(200))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(20), GreyscalePixel(70)),
                                          List(GreyscalePixel(120), GreyscalePixel(170))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }

  test("apply should change brightness by the specified positive amount - all edge cases") {
    val change = 30
    val tested = new BrightnessFilter(change)

    val inputMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(255), GreyscalePixel(225)),
                                          List(GreyscalePixel(226), GreyscalePixel(224))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(255), GreyscalePixel(255)),
                                          List(GreyscalePixel(255), GreyscalePixel(254))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }

  test("apply should change brightness by the specified negative amount - all edge cases") {
    val change = -30
    val tested = new BrightnessFilter(change)

    val inputMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(0), GreyscalePixel(30)),
                                          List(GreyscalePixel(29), GreyscalePixel(31))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(0), GreyscalePixel(0)),
                                          List(GreyscalePixel(0), GreyscalePixel(1))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }

  test("apply should handle an empty input matrix") {
    val change = 30
    val tested = new BrightnessFilter(change)

    val inputMatrix = new ImageMatrix(List.empty[List[GreyscalePixel]])

    val resultMatrix = tested.apply(inputMatrix)

    assert(resultMatrix.getMatrix.isEmpty)
  }

  test("apply should handle a single-row input matrix") {
    val change = -50
    val tested = new BrightnessFilter(change)

    val inputMatrix = new ImageMatrix(List(
                                          List(GreyscalePixel(100), GreyscalePixel(150), GreyscalePixel(200))
    ))

    val resultMatrix = tested.apply(inputMatrix)

    val expectedMatrix = new ImageMatrix(List(
      List(GreyscalePixel(50), GreyscalePixel(100), GreyscalePixel(150))
    ))

    assert(resultMatrix.getMatrix equals expectedMatrix.getMatrix)
  }
}
