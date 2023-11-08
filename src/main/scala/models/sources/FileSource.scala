package models.sources

import java.io.File
import java.nio.file.{Files, Paths}

abstract class FileSource(path:String) extends ImageSource[File]{
  if (!Files.exists(Paths.get(path))) {
    throw new Exception("File does not exist")
  }
  protected val file = new File(path)

}
