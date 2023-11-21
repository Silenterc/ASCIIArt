package models.matrices

import models.pixels.Pixel

import scala.reflect.ClassTag

class ImageMatrix[T <: Pixel[_]](mat: List[List[T]]) {
  val matrix: List[List[T]] = mat

  def map[U <: Pixel[_]](func: T => U): ImageMatrix[U] = {
    new ImageMatrix(matrix.map(_.map(func)))
  }
}
