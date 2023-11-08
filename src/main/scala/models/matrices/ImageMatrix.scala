package models.matrices

import models.pixels.Pixel

class ImageMatrix[T <: Pixel[_]](mat: List[List[T]]) {
  val matrix: List[List[T]] = mat
}
