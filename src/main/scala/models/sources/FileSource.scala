package models.sources

import java.io.File
import java.nio.file.{Files, Paths}

/**
 * File source of Image
 * @param path path to the File, could be absolute or relative
 */
abstract class FileSource(path:String) extends ImageSource[File]{
  protected def mimeType: String
  // Check if the File is legit and as expected
  if (!Files.exists(Paths.get(path)) ||
      !Files.isRegularFile(Paths.get(path)) ||
      !(Files.probeContentType(Paths.get(path)) equals mimeType)) {
    throw new Exception("File is invalid")
  }

  protected val file = new File(path)

}
