package models.matrices

import models.pixels.{GreyscalePixel, Pixel}

import scala.reflect.ClassTag

class ImageMatrix[+T <: Pixel[_]](mat: List[List[T]]) {
  checkRectangularity()
  private val matrix: List[List[T]] = mat

  def mapEvery[U <: Pixel[_]](func: T => U): ImageMatrix[U] = {
    new ImageMatrix(matrix.map(_.map(func)))
  }
  def forall(func: List[T] => Boolean): Boolean = {
    matrix.forall(func)
  }

  def foreach[U](func: List[T] => U): Unit = {
    matrix.foreach[U](func)
  }

  def size(): Int = {
    matrix.size
  }
  def nonEmpty: Boolean = {
    matrix.nonEmpty
  }

  def getMatrix: Seq[Seq[T]] = {
    matrix
  }

  def get(y: Int, x: Int): T = {
    matrix(y)(x)
  }
  def height: Int = {
    matrix.size
  }

  def width: Int = {
    matrix.head.size
  }
  override def toString: String = {
    var finalString = new String()
    for (row <- matrix) {
      for (pixel <- row) {
        finalString += pixel.toString + " "
      }
      finalString += "\n"
    }
    finalString
  }

  private def checkRectangularity(): Boolean= {
    if(!mat.forall(row => row.size == mat.head.size)) {
      throw new IllegalArgumentException("Input Matrix is not a rectangle")
    }
    true
  }
}
