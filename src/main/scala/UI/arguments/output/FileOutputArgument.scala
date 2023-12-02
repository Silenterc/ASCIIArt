package UI.arguments.output

import exporters.FileOutputMatrixExporter

import java.io.File
import java.nio.file.Paths

class FileOutputArgument(path:String) extends OutputArgument[FileOutputMatrixExporter] {
  override def getResult: FileOutputMatrixExporter = {
    try{
      new FileOutputMatrixExporter(new File(path))
    } catch {
      case _: Throwable => throw new IllegalArgumentException(s"The path $path is invalid.")
    }
  }
}
