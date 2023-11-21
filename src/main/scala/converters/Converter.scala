package converters

import models.matrices.ImageMatrix

trait Converter [F <: ImageMatrix[_], T <: ImageMatrix[_]]{
  def convert(imageMatrix: F): T
}
