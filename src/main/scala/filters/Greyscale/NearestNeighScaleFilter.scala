package filters.Greyscale
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel

/**
 * Scaling Filter which uses the Nearest Neighbour interpolation for scaling
 * @param scale the scale
 */
class NearestNeighScaleFilter(scale:Double) extends ScaleFilter(scale) {
  /**
   * Scales the matrix using the Nearest Neighbour Interpolation
   * @param matrix matrix which will have the filter applied on it
   * @return The matrix with the filter applied
   */
  override def apply(matrix: ImageMatrix[GreyscalePixel]): ImageMatrix[GreyscalePixel] = {
    val newHeight = (matrix.height * dimensionScale).toInt
    val newWidth = (matrix.width * dimensionScale).toInt
    val resizedImage = Array.ofDim[GreyscalePixel](newHeight, newWidth)

    for {
      h <- 0 until newHeight
      w <- 0 until newWidth
    } {
      // Calculate the corresponding position in the original image using nearest neighbor interpolation
      val origX = (w / dimensionScale).toInt
      val origY = (h / dimensionScale).toInt
      var value: GreyscalePixel = GreyscalePixel(0)
      // If we are NOT out of bounds, we assign to the new value the corresponding value in the original matrix
      // This check is there just to make sure we can't get some naughty exception
      if (origX < matrix.width && origY < matrix.height) {
        value = matrix.get(origY, origX)
      }
      resizedImage(h)(w) = value.copy()
    }
    new ImageMatrix(resizedImage.map(_.toList).toList)
  }
}
