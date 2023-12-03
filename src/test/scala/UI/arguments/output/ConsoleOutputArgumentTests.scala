package UI.arguments.output

import exporters.StdOutputMatrixExporter
import org.scalatest.FunSuite

class ConsoleOutputArgumentTests {
  class ConsoleOutputArgumentTests extends FunSuite {

    test("getResult() should create a StdOutputMatrixExporter") {
      val outputArg = new ConsoleOutputArgument
      val exporter = outputArg.getResult
      assert(exporter.isInstanceOf[StdOutputMatrixExporter])
    }
  }
}
