package UI.arguments.input

import loaders.{JPEGImageLoader, Loader, PNGImageLoader}
import models.sources.{JPEGFileSource, PNGFileSource}

class FileInputArgument(path: String) extends InputArgument {
  override def getResult: Loader = {
    val mime = path.split("\\.").lastOption.getOrElse("")
    mime match {
      case "png" => new PNGImageLoader(new PNGFileSource(path))
      case "jpeg" => new JPEGImageLoader(new JPEGFileSource(path))
      case _ =>
        throw new Exception(s"Invalid MIME type: $mime. Supported types are .png and .jpeg.")
    }

  }
}
