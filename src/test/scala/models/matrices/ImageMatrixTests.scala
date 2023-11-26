package models.matrices

import models.pixels.{CharPixel, ColorPixel, GreyscalePixel, Pixel}
import org.scalatest.FunSuite

import java.awt.Color

class ImageMatrixTests extends FunSuite{
  test("ImageMatrix throws when it's matrix is not a rectangle") {
    val input = List(List(CharPixel('l'), CharPixel('o'), CharPixel('l')),
                     List(CharPixel('4'), CharPixel('2')),
                     List(CharPixel('l'), CharPixel('o'), CharPixel('l')))
    val exc = intercept[IllegalArgumentException] {
      val tested = new ImageMatrix(input)
    }
    assert(exc.getMessage equals "Input Matrix is not a rectangle")
  }
  test("test getMatrix() for a non empty matrix") {
    val input = List(List(CharPixel('l'), CharPixel('o'), CharPixel('l')),
                     List(CharPixel('4'), CharPixel('2'), CharPixel('0')))

    var tested = new ImageMatrix(input)
    assert(tested.getMatrix equals input)

    tested = new ImageMatrix(List())
    assert(tested.getMatrix.isEmpty)
  }

  test("test getMatrix() for an empty matrix") {
    val tested = new ImageMatrix(List.empty[List[CharPixel]])
    assert(tested.getMatrix.isEmpty)
  }

  test("test mapEvery()") {
    val input = List(List(CharPixel('l'), CharPixel('o'), CharPixel('l')),
                     List(CharPixel('4'), CharPixel('2'), CharPixel('0')))

    val tested = new ImageMatrix(input)
    val testedOutput = tested.mapEvery(pix => CharPixel((pix.value.toInt + 1).toChar))
    val correctOutput = input.map(_.map(pix => CharPixel((pix.value.toInt + 1).toChar)))
    val hardcodedCorrectOutput = List(List(CharPixel('m'), CharPixel('p'), CharPixel('m')),
                                 List(CharPixel('5'), CharPixel('3'), CharPixel('1')))
    assert(testedOutput.getMatrix equals correctOutput)
    assert(testedOutput.getMatrix equals hardcodedCorrectOutput)
  }

  test("forall should return true when all conditions are met") {
    val tested = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4))
    ))

    val result = tested.forall(row => row.forall(pixel => pixel.value > 0))

    assert(result)
  }

  test("forall should return false for any false condition") {
    val tested = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4))
    ))

    val result = tested.forall(row => row.forall(pixel => pixel.value < 4))

    assert(!result)
  }

  test("forall should return true for an empty matrix") {
    val tested = new ImageMatrix(List.empty[List[GreyscalePixel]])

    val result = tested.forall(row => row.forall(pixel => pixel.value > 0))

    assert(result)
  }
  test("foreach should apply the function to each row") {
    val tested = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4))
    ))

    var sum = 0
    tested.foreach(row => sum += row.map(_.value.toInt).sum)

    assert(sum equals 10)
  }
  test("foreach should handle an empty matrix") {
    val tested = new ImageMatrix(List.empty[List[GreyscalePixel]])

    var counter = 0
    tested.foreach(row => counter += 1)

    assert(counter equals 0)
  }
  test("size should return the correct number of rows") {
    val tested = new ImageMatrix(List(
      List(ColorPixel(Color.black), ColorPixel(Color.white)),
      List(ColorPixel(Color.red), ColorPixel(Color.cyan))
    ))

    val result = tested.size()

    assert(result equals 2)
  }

  test("size should return 0 for an empty matrix") {
    val tested = new ImageMatrix(List.empty[List[ColorPixel]])

    val result = tested.size()

    assert(result equals 0)
  }

  test("nonEmpty should return true for a non-empty matrix") {
    val tested = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4))
    ))

    val result = tested.nonEmpty

    assert(result)
  }

  test("nonEmpty should return false for an empty matrix") {
    val tested = new ImageMatrix(List.empty[List[GreyscalePixel]])

    val result = tested.nonEmpty

    assert(!result)
  }

  test("get should return the correct pixel at the specified position") {
    val tested = new ImageMatrix(List(
      List(ColorPixel(Color.black), ColorPixel(Color.white)),
      List(ColorPixel(Color.red), ColorPixel(Color.cyan))
    ))

    val result1 = tested.get(0, 0)
    val result2 = tested.get(0, 1)
    val result3 = tested.get(1, 0)
    val result4 = tested.get(1, 1)
    assert(result1 equals ColorPixel(Color.black))
    assert(result2 equals ColorPixel(Color.white))
    assert(result3 equals ColorPixel(Color.red))
    assert(result4 equals ColorPixel(Color.cyan))
  }

  test("height should return the number of rows") {
    val tested = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4)),
      List(GreyscalePixel(6), GreyscalePixel(6))
    ))

    val result = tested.height

    assert(result equals 3)
  }

  test("width should return the number of columns") {
    val tested = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4)),
      List(GreyscalePixel(6), GreyscalePixel(6))
    ))

    val result = tested.width

    assert(result equals 2)
  }

  test("toString should return correct representation for ColorPixel") {
    val tested = new ImageMatrix(List(
      List(ColorPixel(Color.black), ColorPixel(Color.white)),
      List(ColorPixel(Color.red), ColorPixel(Color.cyan))
    ))

    val result = tested.toString

    val expected = "[0,0,0] [255,255,255] \n[255,0,0] [0,255,255] \n"
    assert(result equals expected)
  }

  test("toString should return correct representation for GreyscalePixel") {
    val tested = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4))
    ))

    val result = tested.toString

    val expected = "1 2 \n3 4 \n"
    assert(result equals expected)
  }

  test("toString should return correct representation for charPixel") {
    val input = List(List(CharPixel('l'), CharPixel('o'), CharPixel('l')),
                     List(CharPixel('4'), CharPixel('2'), CharPixel('0')))

    val tested = new ImageMatrix(input)

    val result = tested.toString

    val expected = "l o l \n4 2 0 \n"
    assert(result equals expected)
  }
}
