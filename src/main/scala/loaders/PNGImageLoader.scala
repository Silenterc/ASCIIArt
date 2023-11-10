package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.PNGFileSource

import java.awt.Color
import java.util
import javax.imageio.ImageIO

class PNGImageLoader extends FileImageLoader[PNGFileSource] {
  override def load(source: PNGFileSource): ImageMatrix[ColorPixel] = {
    val pngImage = ImageIO.read(source.getSource())

    val width = pngImage.getWidth()
    val height = pngImage.getHeight()
    val matrix = Array.ofDim[ColorPixel](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val color:Color = new Color(pngImage.getRGB(x,y))
        matrix(x)(y) = new ColorPixel(color)
      }
    }

    new ImageMatrix[ColorPixel](matrix.map(_.toList).toList)

  }
}
