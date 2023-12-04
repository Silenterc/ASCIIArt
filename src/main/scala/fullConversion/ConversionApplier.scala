package fullConversion

import converters.{ToAsciiConverter, ToGreyscaleConverter}
import filters.ASCII.ASCIIFilter
import filters.Filter
import filters.Greyscale.GreyscaleFilter
import filters.RGB.RGBFilter
import models.matrices.ImageMatrix
import models.pixels.{CharPixel, ColorPixel, GreyscalePixel}

import scala.collection.mutable

/**
 * Class which executes the Color->Greyscale->Ascii Conversion of a Matrix with all the provided Filters
 * @param greyscaleConverter Converter for Color->Greyscale conversion
 * @param toAsciiConverter Converter for Greyscale->Ascii conversion
 */
class ConversionApplier(greyscaleConverter: ToGreyscaleConverter, toAsciiConverter: ToAsciiConverter) {
  /**
   * Queues for storing the provided filters in FIFO fashion
   */
  private var asciiFilters: mutable.Queue[ASCIIFilter] = new mutable.Queue[ASCIIFilter]
  private var greyscaleFilters: mutable.Queue[GreyscaleFilter] = new mutable.Queue[GreyscaleFilter]
  private var rgbFilters: mutable.Queue[RGBFilter] = new mutable.Queue[RGBFilter]

  /**
   * Takes some Filter and correctly stores it
   * @param filter the filter to be registered
   */
  def registerFilter(filter: Filter[_]): Unit = filter match{
    case f:ASCIIFilter => registerASCIIFilter(f)
    case f:GreyscaleFilter => registerGreyscaleFilter(f)
    case f: RGBFilter => registerRGBFilter(f)
    case _ => throw new IllegalArgumentException("Wrong filter type.")
  }
  def registerASCIIFilter(filter: ASCIIFilter): Unit = {
    asciiFilters += filter
  }

  def registerGreyscaleFilter(filter: GreyscaleFilter): Unit = {
    greyscaleFilters += filter
  }

  def registerRGBFilter(filter: RGBFilter): Unit = {
    rgbFilters += filter
  }

  /**
   * Applies all the registered RGB Filters onto ImageMatrix
   * @param imageMatrix matrix to have filters applied on it
   * @return Matrix with the filters applied
   */
  def applyRgbFilters(imageMatrix: ImageMatrix[ColorPixel]): ImageMatrix[ColorPixel] = {
    rgbFilters.foldLeft(imageMatrix)((image, filter) => filter.apply(image))
  }

  /**
   * Applies all the registered Greyscale Filters onto ImageMatrix
   *
   * @param imageMatrix matrix to have filters applied on it
   * @return Matrix with the filters applied
   */
  def applyGreyscaleFilters(imageMatrix: ImageMatrix[GreyscalePixel]): ImageMatrix[GreyscalePixel] = {
    greyscaleFilters.foldLeft(imageMatrix)((image, filter) => filter.apply(image))
  }

  /**
   * Applies all the registered ASCII Filters onto ImageMatrix
   *
   * @param imageMatrix matrix to have filters applied on it
   * @return Matrix with the filters applied
   */
  def applyASCIIFilters(imageMatrix: ImageMatrix[CharPixel]): ImageMatrix[CharPixel] = {
    asciiFilters.foldLeft(imageMatrix)((image, filter) => filter.apply(image))
  }

  /**
   * Executes the whole Color->Greyscale->ASCII conversion including the application of Filters
   * @param imageMatrix matrix to be converted and have filters applied on it
   * @return the final converted matrix
   */
  def applyAll(imageMatrix: ImageMatrix[ColorPixel]): ImageMatrix[CharPixel] = {
    val filteredColorMat = applyRgbFilters(imageMatrix)
    val greyMat = greyscaleConverter.convert(filteredColorMat)
    val filteredGreyMat = applyGreyscaleFilters(greyMat)
    val asciiMat = toAsciiConverter.convert(filteredGreyMat)
    val filteredAsciiMat = applyASCIIFilters(asciiMat)
    filteredAsciiMat
  }


}
