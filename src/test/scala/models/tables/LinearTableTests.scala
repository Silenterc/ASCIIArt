package models.tables

import org.scalatest.FunSuite

class LinearTableTests extends FunSuite{
  test("Construct Bourkes table") {
    val tested:LinearTable = new BourkesTable()
    val bourkesString = new String("$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ")
    var testedOutput: String = new String()
    for (i <- 0 to 255) {
      assert(tested.getChar(i) equals bourkesString(i * bourkesString.length / 256))
      testedOutput += tested.getChar(i)
    }
    val doesContainAll = bourkesString.map(char => if (testedOutput.contains(char)) ' ' else 'f')
    assert(!doesContainAll.contains('f'))
  }

  test("Construct Default table") {
    val tested: LinearTable = new DefaultTable()
    val defaultString = new String("$#mXft1{}[]i!l`. ")
    var testedOutput: String = new String()
    for (i <- 0 to 255) {
      assert(tested.getChar(i) equals defaultString(i * defaultString.length / 256))
      testedOutput += tested.getChar(i)
    }
    val doesContainAll = defaultString.map(char => if (testedOutput.contains(char)) ' ' else 'f')
    assert(!doesContainAll.contains('f'))
  }

  test("Construct \".|\" table") {
    val inputString = new String(".|")
    val tested = new LinearTable(inputString)
    var testedOutput: String = new String()
    for (i <- 0 to 255) {
      assert(tested.getChar(i) equals inputString(i * inputString.length / 256))
      testedOutput += tested.getChar(i)
    }
    val doesContainAll = inputString.map(char => if (testedOutput.contains(char)) ' ' else 'f')
    assert(!doesContainAll.contains('f'))
  }

  test("Construct \"\\?%?%!._\" table") {
    val inputString = new String("\\?%?%!._")
    val tested = new LinearTable(inputString)
    var testedOutput: String = new String()
    for (i <- 0 to 255) {
      assert(tested.getChar(i) equals inputString(i * inputString.length / 256))
      testedOutput += tested.getChar(i)
    }
    val doesContainAll = inputString.map(char => if (testedOutput.contains(char)) ' ' else 'f')
    assert(!doesContainAll.contains('f'))
  }

  test("Construct a 256 char table with repeating \".#\"") {
    val inputString = ".#" * 128
    val tested = new LinearTable(inputString)
    var testedOutput: String = new String()
    for (i <- 0 to 255) {
      var expected: Char = ' '
      if ((i % 2) == 0) expected = '.' else expected = '#'
      assert(tested.getChar(i) equals expected)
      testedOutput += tested.getChar(i)
    }
    val doesContainAll = inputString.map(char => if (testedOutput.contains(char)) ' ' else 'f')
    assert(!doesContainAll.contains('f'))
  }

  test("Throw when empty table String") {
    val exc = intercept[Exception] {
      val tested = new LinearTable("")
    }
    assert(exc.getMessage equals "Invalid Table String")
  }

  test("Throw when too long table String") {
    var inputString = "l" * 257

    var exc = intercept[Exception] {
      val tested = new LinearTable(inputString)
    }
    assert(exc.getMessage equals "Invalid Table String")

    inputString += "w" * 400

    exc = intercept[Exception] {
      val tested2 = new LinearTable(inputString)
    }
    assert(exc.getMessage equals "Invalid Table String")

  }


}


