package loaders

import models.pixels.ColorPixel
import models.sources.{JPEGFileSource, PNGFileSource}
import org.scalatest.FunSuite

import java.awt.Color

class PNGImageLoaderTests extends FunSuite{

  test("Load an existing image and return a matrix filled with something") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/Modus.png"
    val tested = new PNGImageLoader()
    val source = new PNGFileSource(path)
    val imageMatrix = tested.load(source)
    assert(imageMatrix.nonEmpty)

  }

  test("Load an existing 8x2 image and return the corresponding matrix") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/black8x2.png"
    val tested = new PNGImageLoader()
    val source = new PNGFileSource(path)
    val imageMatrix = tested.load(source)
    assert(imageMatrix.nonEmpty)
    assert(imageMatrix.size() == 2)
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
