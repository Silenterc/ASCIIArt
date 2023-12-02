package UI.arguments.output

import exporters.StdOutputMatrixExporter

class ConsoleOutputArgument extends OutputArgument[StdOutputMatrixExporter] {
  override def getResult: StdOutputMatrixExporter = {
    new StdOutputMatrixExporter
  }
}
