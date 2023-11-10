package models.sources

import org.scalatest.FunSuite

class PNGFileSourceTests extends FunSuite{

  test ("Succesfully load image") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/sus.png"
    val tested = new PNGFileSource(path)
    val source = tested.getSource()
    assert(source.isFile)
    assert(source.exists())
    assert(source.getPath() equals path)
  }

  test("Throw when no file like that exists") {
    val path = "/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/SocialLife.png"
    val exc = intercept[Exception] {
      val tested = new PNGFileSource(path)
    }
    assert(exc.getMessage equals "File does not exist")

  }
}
