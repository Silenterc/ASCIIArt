package models.pixels
import org.scalatest.FunSuite
import models.pixels.CharPixel
class CharPixelTests extends FunSuite{

  test("Construct") {
    val pix = new CharPixel('L')
    assert(pix.value == 'L')

  }
  test("Assign value") {
    val pix = new CharPixel('L')
    pix.value = 'O'
    assert(pix.value =='O')


  }

}
