package models.sources

import org.scalatest.FunSuite

class JPEGFileSourceTests extends FunSuite{

  test ("Succesfully load image") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/Modus.jpeg"
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
    assert(exc.getMessage equals "File does not exist")

  }

  test("Throw when path is empty") {
    val path = ""
    val exc = intercept[Exception] {
      val tested = new JPEGFileSource(path)
    }
    assert(exc.getMessage equals "File does not exist")

  }
  test("Throw when path is folder") {
    val path = "/Users"
    val exc = intercept[Exception] {
      val tested = new JPEGFileSource(path)
    }
    assert(exc.getMessage equals "File does not exist")

  }
}
