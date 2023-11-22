package Main

import converters.{ToAsciiLinearConverter, ToGrayscaleConverter}
import loaders.{FileImageLoader, JPEGImageLoader, PNGImageLoader}
import models.LinearTable
import models.pixels.{ColorPixel, Pixel}
import models.sources.{JPEGFileSource, PNGFileSource}

import java.io.{File, PrintWriter}

object Main extends App {
  //println("Hello there")
  val loader = new JPEGImageLoader()
  val im = loader.load(new JPEGFileSource("/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/bird.jpeg"))
  val gCon = new ToGrayscaleConverter
  val aCon = new ToAsciiLinearConverter()
  val image = aCon.convert(gCon.convert(im))
  val writer = new PrintWriter(new File("/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/bird"))
  val matrix = image.getMatrix
  for (row <- matrix) {
    for (pixel <- row) {
      writer.print(pixel.value + " ")
      //print(pixel.value)
    }
    //println()
    writer.println()
  }
  writer.close()
}