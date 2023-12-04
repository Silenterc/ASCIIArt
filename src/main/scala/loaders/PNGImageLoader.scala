package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.PNGFileSource

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import java.util
import javax.imageio.ImageIO
/**
 * Loader of an ImageMatrix from a PNG File
 */
class PNGImageLoader(source: PNGFileSource) extends FileImageLoader {
  /**
   * Removes transparency from an image
   * @param pngImage the Image which will have it's transparency removed
   */
  def removeTransparency(pngImage: BufferedImage): Unit = {
    val anotherOne = new BufferedImage(pngImage.getWidth, pngImage.getHeight, BufferedImage.TYPE_INT_RGB)
    val graphicsObject = anotherOne.createGraphics()
    graphicsObject.setColor(Color.WHITE)
    graphicsObject.fillRect(0, 0, anotherOne.getWidth(), anotherOne.getHeight())
    graphicsObject.drawImage(pngImage, 0, 0, null)
    graphicsObject.dispose()
  }

  /**
   * Loads a PNG Image and transforms it into a ColorPixel Matrix
   * @return corresponding ColorPixel Matrix
   */
  override def load(): ImageMatrix[ColorPixel] = {
    val pngImage = ImageIO.read(source.getSource)
    removeTransparency(pngImage)
    val width = pngImage.getWidth
    val height = pngImage.getHeight
    val matrix = Array.ofDim[ColorPixel](height, width)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val color:Color = new Color(pngImage.getRGB(x,y))
        // Our matrix has rows and then cols, so (y)(x)
        matrix(y)(x) = ColorPixel(color)
      }
    }

    new ImageMatrix[ColorPixel](matrix.map(_.toList).toList)

  }
}
