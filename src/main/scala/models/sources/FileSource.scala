package models.sources

import java.io.File
import java.nio.file.{Files, Paths}

abstract class FileSource(path:String) extends ImageSource[File]{
  protected def mimeType: String
  if (!Files.exists(Paths.get(path)) ||
      !Files.isRegularFile(Paths.get(path)) ||
      !(Files.probeContentType(Paths.get(path)) equals mimeType)) {
    throw new Exception("File is invalid")
  }

  protected val file = new File(path)

}
