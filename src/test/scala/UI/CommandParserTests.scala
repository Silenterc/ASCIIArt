package UI

import UI.arguments.input.{FileInputArgument, RandomInputArgument}
import UI.arguments.tables.{BourkesTableArgument, DefaultTableArgument, MyNonLinearTableArgument, TableArgument}
import loaders.{JPEGImageLoader, PNGImageLoader}
import models.tables.Table
import org.scalatest.FunSuite

class CommandParserTests extends FunSuite {
  val map: Map[String, TableArgument[Table]] = Map("default" -> new DefaultTableArgument, "bourkes" -> new BourkesTableArgument, "nonlinear" -> new MyNonLinearTableArgument)
  test("Parsing valid input argument .png") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image", "src/test/pics/Modus.png"))
    val result = parser.getInputArgument
    assert(result.isInstanceOf[FileInputArgument])
    assert(result.getResult.isInstanceOf[PNGImageLoader])
  }

  test("Parsing valid input argument .jpeg") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image", "src/test/pics/Modus.jpeg"))
    val result = parser.getInputArgument
    assert(result.isInstanceOf[FileInputArgument])
    assert(result.getResult.isInstanceOf[JPEGImageLoader])
  }

  test("Parsing multiple input arguments throws") {
    val parser = new CommandParser(map)
    val exc = intercept[IllegalArgumentException] {
      parser.parse(Array("--image", "src/test/pics/Modus.jpeg", "--invert", "--image", "src/test/pics/bird.jpeg"))
    }
    assert(exc.getMessage equals "You have input multiple Image Sources. You may only input one.")
  }


  test("Parsing random image source") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image-random"))
    assert(parser.getInputArgument.isInstanceOf[RandomInputArgument])
  }

  test("Parsing Bourkes table") {
//    val parser = new CommandParser(map)
//    parser.parse(Array("--table", "bourkes"))
//    assert(parser.getTable.isInstanceOf[BourkesTableArgument])
  }

  test("Parsing nonlinear table") {
//    val parser = new CommandParser(map)
//    parser.parse(Array("--table", "nonlinear"))
//    assert(parser.getTable.isInstanceOf[MyNonLinearTableArgument])
  }

  // Add more tests for other scenarios based on your specific use cases
}
