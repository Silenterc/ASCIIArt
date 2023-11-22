package models.sources

import java.io.File

class PNGFileSource(path:String) extends FileSource(path) {
  override def mimeType = "image/png"

  override def getSource(): File = file
}
