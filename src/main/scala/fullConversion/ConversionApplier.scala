package fullConversion

import converters.{ToAsciiConverter, ToGreyscaleConverter}
import filters.ASCII.ASCIIFilter
import filters.Greyscale.GreyscaleFilter
import filters.RGB.RGBFilter
import models.matrices.ImageMatrix
import models.pixels.{CharPixel, ColorPixel, GreyscalePixel}

import scala.collection.mutable


class ConversionApplier(greyscaleConverter: ToGreyscaleConverter, toAsciiConverter: ToAsciiConverter) {
  private var asciiFilters: mutable.Queue[ASCIIFilter] = new mutable.Queue[ASCIIFilter]
  private var greyscaleFilters: mutable.Queue[GreyscaleFilter] = new mutable.Queue[GreyscaleFilter]
  private var rgbFilters: mutable.Queue[RGBFilter] = new mutable.Queue[RGBFilter]

  def registerFilter(filter: ASCIIFilter): Unit = {
    asciiFilters += filter
  }

  def registerFilter(filter: GreyscaleFilter): Unit = {
    greyscaleFilters += filter
  }

  def registerFilter(filter: RGBFilter): Unit = {
    rgbFilters += filter
  }
  def applyRgbFilters(imageMatrix: ImageMatrix[ColorPixel]): ImageMatrix[ColorPixel] = {
    rgbFilters.foldLeft(imageMatrix)((image, filter) => filter.apply(image))
  }
  def applyGreyscaleFilters(imageMatrix: ImageMatrix[GreyscalePixel]): ImageMatrix[GreyscalePixel] = {
    greyscaleFilters.foldLeft(imageMatrix)((image, filter) => filter.apply(image))
  }
  def applyASCIIFilters(imageMatrix: ImageMatrix[CharPixel]): ImageMatrix[CharPixel] = {
    asciiFilters.foldLeft(imageMatrix)((image, filter) => filter.apply(image))
  }
  def applyAll(imageMatrix: ImageMatrix[ColorPixel]): ImageMatrix[CharPixel] = {
    val filteredColorMat = applyRgbFilters(imageMatrix)
    val greyMat = greyscaleConverter.convert(filteredColorMat)
    val filteredGreyMat = applyGreyscaleFilters(greyMat)
    val asciiMat = toAsciiConverter.convert(filteredGreyMat)
    val filteredAsciiMat = applyASCIIFilters(asciiMat)
    filteredAsciiMat
  }


}
