package UI.arguments.output

import exporters.FileOutputMatrixExporter
import org.scalatest.FunSuite

import java.io.File

class FileOutputArgumentTests extends FunSuite {

  test("getResult() should create a FileOutputMatrixExporter for a valid path") {
    val outputArg = new FileOutputArgument("src/test/pics/output")
    val exporter = outputArg.getResult
    assert(exporter.isInstanceOf[FileOutputMatrixExporter])
    val file = new File("src/test/pics/output")
    file.delete()
  }

  test("getResult() should create a FileOutputMatrixExporter for a valid path - txt") {
    val outputArg = new FileOutputArgument("src/test/pics/output.txt")
    val exporter = outputArg.getResult
    assert(exporter.isInstanceOf[FileOutputMatrixExporter])
    val file = new File("src/test/pics/output.txt")
    file.delete()
  }

  test("FileOutputArgument should throw for an invalid path") {
    val exc = intercept[IllegalArgumentException] {
      new FileOutputArgument("é./o/ //íé:.").getResult
    }
    assert(exc.getMessage equals "The path é./o/ //íé:. is invalid.")
  }

}
