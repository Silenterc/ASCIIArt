package models.pixels

/**
 * Pixel of some value T
 * @tparam T some value
 */
trait Pixel[T] {
  var value:T = _
  def toString: String
}
