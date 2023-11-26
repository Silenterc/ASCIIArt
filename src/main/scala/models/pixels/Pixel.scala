package models.pixels

trait Pixel[T] {
  var value:T = _
  def toString: String
}
