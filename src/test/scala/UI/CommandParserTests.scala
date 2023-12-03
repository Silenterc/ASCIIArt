package UI

import UI.arguments.filters.{BrightnessFilterArgument, FilterArgument, InvertFilterArgument, NearestNeighScaleFilterArgument}
import UI.arguments.input.{FileInputArgument, RandomInputArgument}
import UI.arguments.output.{ConsoleOutputArgument, FileOutputArgument}
import UI.arguments.tables.{BourkesTableArgument, DefaultTableArgument, MyNonLinearTableArgument, TableArgument}
import filters.Filter
import filters.Greyscale.NearestNeighScaleFilter
import loaders.{JPEGImageLoader, PNGImageLoader}
import models.pixels.GreyscalePixel
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

  test("Getting InputArguments when none were parsed throws") {
    val parser = new CommandParser(map)
    val exc = intercept[NoSuchElementException] {
      parser.parse(Array("--output-console", "--table", "bourkes", "--invert"))
      val arg = parser.getInputArgument
    }
    assert(exc.getMessage equals "You have not input a file source")
  }


  test("Parsing random image source") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image-random"))
    assert(parser.getInputArgument.isInstanceOf[RandomInputArgument])
  }

  test("Parsing multiple input arguments of different types throws") {
    val parser = new CommandParser(map)
    val exc = intercept[IllegalArgumentException] {
      parser.parse(Array("--image", "src/test/pics/Modus.jpeg", "--invert", "--image-random"))
    }
    assert(exc.getMessage equals "You have input multiple Image Sources. You may only input one.")
  }

  test("Parsing Scale") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image", "src/test/pics/Modus.jpeg","--scale", "4.20"))
    val arr = parser.getFilterArguments
    assert(arr.size == 1)
    assert(arr.head.isInstanceOf[NearestNeighScaleFilterArgument])
  }

  test("Parsing Invert") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image", "src/test/pics/Modus.jpeg", "--invert"))
    val arr = parser.getFilterArguments
    assert(arr.size == 1)
    assert(arr.head.isInstanceOf[InvertFilterArgument])
  }

  test("Parsing Brightness") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image", "src/test/pics/Modus.jpeg", "--brightness", "9"))
    val arr = parser.getFilterArguments
    assert(arr.size == 1)
    assert(arr.head.isInstanceOf[BrightnessFilterArgument])
  }

  test("Parsing Output to File") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image", "src/test/pics/Modus.jpeg", "--output-file", "src/test/pics/file"))
    val arr = parser.getOutputArguments
    assert(arr.size == 1)
    assert(arr.head.isInstanceOf[FileOutputArgument])
  }

  test("Parsing Output to Console") {
    val parser = new CommandParser(map)
    parser.parse(Array("--image", "src/test/pics/Modus.jpeg", "--output-console"))
    val arr = parser.getOutputArguments
    assert(arr.size == 1)
    assert(arr.head.isInstanceOf[ConsoleOutputArgument])
  }

  test("Parsing Output to both File and Console") {
    val parser = new CommandParser(map)
    parser.parse(Array("--output-file", "src/test/pics/file", "--image", "src/test/pics/Modus.jpeg", "--output-console"))
    val arr = parser.getOutputArguments
    assert(arr.size == 2)
    assert(arr.head.isInstanceOf[FileOutputArgument])
    assert(arr(1).isInstanceOf[ConsoleOutputArgument])
  }

  test("Parsing Output to many Files and Consoles") {
    val parser = new CommandParser(map)
    parser.parse(Array("--output-file", "src/test/pics/file", "--output-file", "src/test/pics/file2",
                       "--image", "src/test/pics/Modus.jpeg", "--output-console",
                       "--output-file", "src/test/pics/file3","--output-console"))
    val arr = parser.getOutputArguments
    assert(arr.size == 5)
    assert(arr.head.isInstanceOf[FileOutputArgument])
    assert(arr(1).isInstanceOf[FileOutputArgument])
    assert(arr(2).isInstanceOf[ConsoleOutputArgument])
    assert(arr(3).isInstanceOf[FileOutputArgument])
    assert(arr(4).isInstanceOf[ConsoleOutputArgument])
  }

  test("Parsing Bourkes table") {
    val parser = new CommandParser(map)
    parser.parse(Array("--table", "bourkes"))
    assert(parser.getTable.isInstanceOf[BourkesTableArgument])
  }

  test("Parsing nonlinear table") {
    val parser = new CommandParser(map)
    parser.parse(Array("--table", "nonlinear"))
    assert(parser.getTable.isInstanceOf[MyNonLinearTableArgument])
  }

}
