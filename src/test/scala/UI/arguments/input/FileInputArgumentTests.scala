package UI.arguments.input

import loaders.{JPEGImageLoader, PNGImageLoader}
import org.scalatest.FunSuite

class FileInputArgumentTests extends FunSuite{
  test("getResult() should create a PNGImageLoader for valid path with .png extension") {
    val inputArg = new FileInputArgument("src/test/pics/Modus.png")
    val loader = inputArg.getResult
    assert(loader.isInstanceOf[PNGImageLoader])
  }

  test("getResult() should create a JPEGImageLoader for valid path with .jpeg extension") {
    val inputArg = new FileInputArgument("src/test/pics/Modus.jpeg")
    val loader = inputArg.getResult
    assert(loader.isInstanceOf[JPEGImageLoader])
  }

  test("getResult() should throw for invalid file extension - gif") {
    val exc = intercept[Exception] {
      new FileInputArgument("src/test/pics/Modus.gif").getResult
    }
    assert(exc.getMessage equals "Invalid MIME type: gif. Supported types are .png and .jpeg.")
  }

  test("getResult() should throw for invalid file extension - jpg") {
    val exc = intercept[Exception] {
      new FileInputArgument("src/test/pics/Modus.jpg").getResult
    }
    assert(exc.getMessage equals "Invalid MIME type: jpg. Supported types are .png and .jpeg.")
  }

  test("getResult() should throw for empty path") {
    val exc = intercept[Exception] {
      new FileInputArgument("").getResult
    }
    assert(exc.getMessage equals "Invalid MIME type: . Supported types are .png and .jpeg.")
  }

  test("getResult() should throw for path without extension") {
    val exc = intercept[Exception] {
      new FileInputArgument("src/test/pics").getResult
    }
    assert(exc.getMessage equals "Invalid MIME type: src/test/pics. Supported types are .png and .jpeg.")
  }
}
