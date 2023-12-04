package models.matrices

import models.pixels.Pixel

/**
 * Our Image class, used for representing the Image with various Pixels
 * @param mat 2d List from which the corresponding matrix gets built
 * @tparam T The Pixel type within the matrix
 */
class ImageMatrix[+T <: Pixel[_]](mat: List[List[T]]) {
  checkRectangularity()
  private val matrix: List[List[T]] = mat

  /**
   * Applies function func to every element of this matrix
   * @param func function to be applied
   * @tparam U The result of the function
   * @return New ImageMatrix with func applied to each pixel
   */
  def mapEvery[U <: Pixel[_]](func: T => U): ImageMatrix[U] = {
    new ImageMatrix(matrix.map(_.map(func)))
  }

  /**
   * Checks if func is true for each row of this matrix
   * @param func function which returns a bool for a row
   * @return Was func true for each row?
   */
  def forall(func: List[T] => Boolean): Boolean = {
    matrix.forall(func)
  }

  /**
   * Apply func to each row for its side effects
   * @param func applied function
   * @tparam U What the function should return
   */
  def foreach[U](func: List[T] => U): Unit = {
    matrix.foreach[U](func)
  }

  /**
   *
   * @return the size of matrix (number of rows)
   */
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

  /**
   * @return the height of matrix (number of rows)
   */
  def height: Int = {
    matrix.size
  }

  /**
   * @return the width of matrix (number of columns)
   */
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

  /**
   * Checks whether the matrix really is a rectangle (all the rows have the same size)
   * @return is it a rectangle?
   */
  private def checkRectangularity(): Boolean = {
    if(!mat.forall(row => row.size == mat.head.size)) {
      throw new IllegalArgumentException("Input Matrix is not a rectangle")
    }
    true
  }
}
