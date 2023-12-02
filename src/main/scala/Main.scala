package Main

import UI.CommandParser
import converters.{ToAsciiConverter, ToGreyscaleConverter}
import filters.Greyscale.NearestNeighScaleFilter
import fullConversion.ConversionApplier
import loaders.{FileImageLoader, JPEGImageLoader, PNGImageLoader}
import models.pixels.{ColorPixel, Pixel}
import models.sources.{JPEGFileSource, PNGFileSource}
import models.tables.LinearTable

import java.io.{File, PrintWriter}

object Main extends App {

  //println("Hello there")
//  val loader = new PNGImageLoader()
//  val im = loader.load(new PNGFileSource("/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/star.png"))
//  val gCon = new ToGreyscaleConverter
//  val aCon = new ToAsciiConverter(new LinearTable())
//  val applier = new ConversionApplier(gCon, aCon)
//  applier.registerFilter(new NearestNeighScaleFilter(4))
//  val image = applier.applyAll(im)
//  val exporter = new FileOutputMatrixExporter(new File("/Users/silenter/Desktop/OOP/ascii-art-zimaluk-1/src/test/pics/star"))
//  exporter.export(image)

  //val p = new CommandParser
  //p.parse(args)
}