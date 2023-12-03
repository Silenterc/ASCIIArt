package Main

import UI.CommandParser
import UI.arguments.tables.{BourkesTableArgument, DefaultTableArgument, MyNonLinearTableArgument, TableArgument}
import converters.{ToAsciiConverter, ToGreyscaleConverter}
import filters.Greyscale.NearestNeighScaleFilter
import fullConversion.ConversionApplier
import loaders.{FileImageLoader, JPEGImageLoader, PNGImageLoader}
import models.pixels.{ColorPixel, Pixel}
import models.sources.{JPEGFileSource, PNGFileSource}
import models.tables.{LinearTable, Table}

import java.io.{File, PrintWriter}

object Main extends App {
  val allowedTables: Map[String, TableArgument[Table]] = Map("default" -> new DefaultTableArgument,
                                                             "bourkes" -> new BourkesTableArgument,
                                                             "nonlinear" -> new MyNonLinearTableArgument)
  val parser = new CommandParser(allowedTables)
  parser.parse(args)
  val inputArg = parser.getInputArgument
  val loader = inputArg.getResult
  val filters = parser.getFilterArguments
  val outputArgs = parser.getOutputArguments
  val tableArg = parser.getTable
  val table = tableArg.getResult
  val fullApplier = new ConversionApplier(new ToGreyscaleConverter, new ToAsciiConverter(table))
  filters.foreach(f => fullApplier.registerFilter(f.getResult))
  val inputMat = loader.load()
  val resultMatrix = fullApplier.applyAll(inputMat)
  outputArgs.foreach(e => e.getResult.export(resultMatrix))

}