package models.sources

import java.io.File
/**
 * PNG File source of Image
 * @param path path to the File, could be absolute or relative
 */
class PNGFileSource(path:String) extends FileSource(path) {
  override def mimeType = "image/png"

  override def getSource: File = file
}
