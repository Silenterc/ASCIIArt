package models.sources

import java.io.File
import java.nio.file.Files

class JPEGFileSource(path:String) extends FileSource(path) {
  override def mimeType = "image/jpeg"

  override def getSource(): File = file
}
