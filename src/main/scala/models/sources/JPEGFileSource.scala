package models.sources

import java.io.File

class JPEGFileSource(path:String) extends FileSource(path) {


  override def getSource(): File = file
}
