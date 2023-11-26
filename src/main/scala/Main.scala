package Main

import converters.{ToAsciiLinearConverter, ToGreyscaleConverter}
import exporters.{FileOutputMatrixExporter, StdOutputExporter, StreamMatrixExporter}
import loaders.{FileImageLoader, JPEGImageLoader, PNGImageLoader}
import models.pixels.{ColorPixel, Pixel}
import models.sources.{JPEGFileSource, PNGFileSource}
import models.tables.LinearTable

import java.io.{File, PrintWriter}

object Main extends App {
  //println("Hello there")
  val loader = new PNGImageLoader()
  val im = loader.load(new PNGFileSource("/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/facebook.png"))
  val gCon = new ToGreyscaleConverter
  val aCon = new ToAsciiLinearConverter()
  val image = aCon.convert(gCon.convert(im))
  val exporter = new FileOutputMatrixExporter(new File("/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/facebook"))
  exporter.export(image)
}