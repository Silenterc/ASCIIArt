package loaders

import loaders.JPEGImageLoader
import models.pixels.ColorPixel
import models.sources.JPEGFileSource
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.FunSuite

import java.awt.Color
import java.io.File

class JPEGImageLoaderTests extends FunSuite{

  test("Load an existing image and return a matrix filled with something") {
    val path = "src/test/pics/Modus.jpeg"
    val source = new JPEGFileSource(path)
    val tested = new JPEGImageLoader(source)
    val imageMatrix = tested.load()
    assert(imageMatrix.nonEmpty)

  }
  test("Load an existing 8x2 image and return the corresponding matrix") {
    val path = "src/test/pics/black8x2.jpeg"
    val source = new JPEGFileSource(path)
    val tested = new JPEGImageLoader(source)
    val imageMatrix = tested.load()
    assert(imageMatrix.nonEmpty)
    Predef.assert(imageMatrix.size() == 2)
    assert(imageMatrix.forall(list => list.size == 8))

    val correctMatrix = List(List(ColorPixel(Color.black), ColorPixel(Color.black), ColorPixel(Color.black),
                                  ColorPixel(Color.black), ColorPixel(Color.black), ColorPixel(Color.black),
                                  ColorPixel(Color.black), ColorPixel(Color.black)),
                             List(ColorPixel(Color.black), ColorPixel(Color.black), ColorPixel(Color.black),
                                  ColorPixel(Color.black), ColorPixel(Color.black), ColorPixel(Color.black),
                                  ColorPixel(Color.black), ColorPixel(Color.black)))
    assert(imageMatrix.getMatrix equals correctMatrix)

  }

}
