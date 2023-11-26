package models.sources

import org.scalatest.FunSuite

class JPEGFileSourceTests extends FunSuite{

  test ("Succesfully load image from absolute path") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/Modus.jpeg"
    val tested = new JPEGFileSource(path)
    val source = tested.getSource()
    assert(source.isFile)
    assert(source.exists())
    assert(source.getPath equals path)
  }

  test("Succesfully load image from relative path") {
    val path = "src/test/pics/Modus.jpeg"
    val tested = new JPEGFileSource(path)
    val source = tested.getSource()
    assert(source.isFile)
    assert(source.exists())
    assert(source.getPath equals path)
  }

  test("Throw when no file like that exists") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/SocialLife.jpeg"
    val exc = intercept[Exception] {
      val tested = new JPEGFileSource(path)
    }
    assert(exc.getMessage equals "File is invalid")
  }

  test("Throw when file has wrong ending = mimeType") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/sus.png"
    val exc = intercept[Exception] {
      val tested = new JPEGFileSource(path)
    }
    assert(exc.getMessage equals "File is invalid")
  }

  test("Throw when path is empty") {
    val path = ""
    val exc = intercept[Exception] {
      val tested = new JPEGFileSource(path)
    }
    assert(exc.getMessage equals "File is invalid")

  }
  test("Throw when path is folder") {
    val path = "/Users"
    val exc = intercept[Exception] {
      val tested = new JPEGFileSource(path)
    }
    assert(exc.getMessage equals "File is invalid")

  }
}
