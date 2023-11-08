package models.sources

import java.io.File
import java.nio.file.{Files, Paths}

class FileSource(path:String) extends ImageSource[File]{
  if (! Files.exists(Paths.get(path))) {
    throw new Exception("File does not exist")
  }
  private val file:File = new File(path)


  override def getSource(): File = file
}
