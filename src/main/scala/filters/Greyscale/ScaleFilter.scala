package filters.Greyscale

/**
 * Filter which scales the ImageMatrix by scale
 * @param scale the scale
 */
abstract class ScaleFilter(scale:Double) extends GreyscaleFilter {
  /**
   * For each matrix's dimension (width & height), we care about the sqrt of scale
   * f.e. if the scale is 4, we scale the height by 2 and the width by 2
   */
  val dimensionScale:Double = Math.sqrt(scale)
}
