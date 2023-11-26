package models.pixels

import org.scalatest.FunSuite

import java.awt.Color

class ColorPixelTests extends FunSuite{

  test("Construct") {
    val pix = new ColorPixel(new Color(0, 255, 255))
    assert(pix.value.equals(Color.cyan))
    assert(pix.value equals new Color(0, 255, 255))

  }
  test("Assign value") {
    val pix = new ColorPixel(Color.cyan)
    pix.value = Color.blue
    assert(pix.value equals Color.blue)
    assert(pix.value equals new Color(0,0,255))
    pix.value = new Color(255, 0, 0)
    assert(pix.value equals Color.red)
    assert(pix.value equals new Color(255, 0, 0))
  }
  test("test toString") {
    val pix = ColorPixel(Color.black)
    assert(pix.toString equals "[0,0,0]")
    pix.value = Color.cyan
    assert(pix.toString equals "[0,255,255]")
  }

}
