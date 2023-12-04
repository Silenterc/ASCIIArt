package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.MyRandomSource
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.FunSuite

import java.awt.Color

class MyRandomImageLoaderTests extends FunSuite{
  test("load() returns some random Matrix") {
    val source = new MyRandomSource
    val loader = new MyRandomImageLoader(source)
    val result = loader.load()
    assert(result.height > 0 && result.width > 0)
  }

  test("load() returns the expected (mocked) Matrix") {
    val source = mock[MyRandomSource]
    val matrix = new ImageMatrix(List(List(ColorPixel(Color.cyan), ColorPixel(Color.black)),
                                 List(ColorPixel(Color.red), ColorPixel(Color.pink)),
                                 List(ColorPixel(Color.white), ColorPixel(Color.yellow))))
    when(source.getSource).thenReturn(matrix)
    val loader = new MyRandomImageLoader(source)
    val result = loader.load()
    assert(matrix equals result)
  }
}
