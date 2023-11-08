package models.sources

import java.io.File

class PNGFileSource(path:String) extends FileSource(path) {


  override def getSource(): File = file
}
