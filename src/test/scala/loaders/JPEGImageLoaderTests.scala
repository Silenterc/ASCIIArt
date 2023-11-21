package loaders

import loaders.JPEGImageLoader
import models.sources.JPEGFileSource
import org.scalatest.FunSuite

class JPEGImageLoaderTests extends FunSuite{

  test("Load an existing image and return a matrix filled with something") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/Modus.jpeg"
    val tested = new JPEGImageLoader()
    val source = new JPEGFileSource(path)
    val imageMatrix = tested.load(source)
    assert(imageMatrix.matrix.nonEmpty)

  }

  test("Load an existing image and return the corresponding matrix") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/black8x2.jpeg"
    val tested = new JPEGImageLoader()
    val source = new JPEGFileSource(path)
    val imageMatrix = tested.load(source)
    assert(imageMatrix.matrix.nonEmpty)
    assert(imageMatrix.matrix.size == 2)
    assert(imageMatrix.matrix.forall(list => list.size == 8))
    //TODO Complete check of values

  }

}
