package filters.Greyscale

abstract class ScaleFilter(scale:Double) extends GreyscaleFilter {
  protected val dimensionScale:Double = Math.sqrt(scale)
}
