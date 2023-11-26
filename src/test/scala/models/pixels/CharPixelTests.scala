package models.pixels
import org.scalatest.FunSuite
import models.pixels.CharPixel
class CharPixelTests extends FunSuite{

  test("Construct") {
    val pix = CharPixel('L')
    assert(pix.value == 'L')

  }
  test("Assign value") {
    val pix = CharPixel('L')
    pix.value = 'O'
    assert(pix.value =='O')
    pix.value = 'K'
    assert(pix.value == 'K')
  }
  test("test toString") {
    val pix = CharPixel('L')
    assert(pix.toString equals "L")
    pix.value = '4'
    assert(pix.toString equals "4")
  }

}
