package filters.Greyscale

abstract class ScaleFilter(scale:Double) extends GreyscaleFilter {
  val dimensionScale:Double = Math.sqrt(scale)
}
