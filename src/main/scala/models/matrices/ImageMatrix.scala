package models.matrices

import models.pixels.Pixel

import scala.reflect.ClassTag

class ImageMatrix[T <: Pixel[_]](mat: List[List[T]]) {
  private val matrix: List[List[T]] = mat

  def map[U <: Pixel[_]](func: T => U): ImageMatrix[U] = {
    new ImageMatrix(matrix.map(_.map(func)))
  }

  def forall(func: List[T] => Boolean): Boolean = {
    matrix.forall(func)
  }

  def size(): Int = {
    matrix.size
  }
  def nonEmpty: Boolean ={
    matrix.nonEmpty
  }

  def getMatrix: Seq[Seq[T]] = {
    matrix
  }
}
