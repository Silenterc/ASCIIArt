package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.{JPEGFileSource, PNGFileSource}

import java.awt.Color
import javax.imageio.ImageIO
/**
 * Loader of an ImageMatrix from a JPEG File
 */
class JPEGImageLoader(source: JPEGFileSource) extends FileImageLoader {
  /**
   * Loads a JPEG Image and transforms it into a ColorPixel Matrix
   *
   * @return corresponding ColorPixel Matrix
   */
  override def load(): ImageMatrix[ColorPixel] = {
    val pngImage = ImageIO.read(source.getSource)

    val width = pngImage.getWidth
    val height = pngImage.getHeight
    val matrix = Array.ofDim[ColorPixel](height, width)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val color: Color = new Color(pngImage.getRGB(x, y))
        // Our matrix has rows and then cols, so (y)(x)
        matrix(y)(x) = ColorPixel(color)
      }
    }
    new ImageMatrix[ColorPixel](matrix.map(_.toList).toList)

  }
}
