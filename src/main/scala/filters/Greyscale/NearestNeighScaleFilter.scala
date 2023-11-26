package filters.Greyscale
import models.matrices.ImageMatrix
import models.pixels.GreyscalePixel

class NearestNeighScaleFilter(scale:Double) extends ScaleFilter(scale) {
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
      if (origX < matrix.width && origY < matrix.height) {
        value = matrix.get(origY, origX)
      }
      resizedImage(h)(w) = value.copy()
    }
    new ImageMatrix(resizedImage.map(_.toList).toList)
  }
}
