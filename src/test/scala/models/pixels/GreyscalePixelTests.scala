package models.pixels

import org.scalatest.FunSuite

class GreyscalePixelTests extends FunSuite{

  test("Construct") {
    val pix = new GreyscalePixel(27.7)
    assert(pix.value equals 27.7)

  }
  test("Assign value") {
    val pix = new GreyscalePixel(27.7)
    pix.value = 40.2
    assert(pix.value == 40.2)
    pix.value = 20.9
    assert(pix.value == 20.9)
  }
  test("test toString") {
    val pix = GreyscalePixel(25)
    assert(pix.toString equals "25")
    pix.value = 4.20
    assert(pix.toString equals "4")
  }

}
