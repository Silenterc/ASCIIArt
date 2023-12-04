package models.sources

import java.io.File
import java.nio.file.Files
/**
 * JPEG File source of Image
 * @param path path to the File, could be absolute or relative
 */
class JPEGFileSource(path:String) extends FileSource(path) {
  override def mimeType = "image/jpeg"

  override def getSource: File = file
}
