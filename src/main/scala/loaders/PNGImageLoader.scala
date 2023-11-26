package loaders

import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.PNGFileSource

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import java.util
import javax.imageio.ImageIO

class PNGImageLoader extends FileImageLoader[PNGFileSource] {

  def removeTransparency(pngImage: BufferedImage): Unit = {
    val anotherOne = new BufferedImage(pngImage.getWidth, pngImage.getHeight, BufferedImage.TYPE_INT_RGB)
    val graphicsObject = anotherOne.createGraphics()
    graphicsObject.setColor(Color.YELLOW)
    graphicsObject.fillRect(0, 0, anotherOne.getWidth(), anotherOne.getHeight())
    graphicsObject.drawImage(pngImage, 0, 0, null)
    graphicsObject.dispose()
  }

  override def load(source: PNGFileSource): ImageMatrix[ColorPixel] = {
    val pngImage = ImageIO.read(source.getSource())
    removeTransparency(pngImage)
    val width = pngImage.getWidth()
    val height = pngImage.getHeight()
    val matrix = Array.ofDim[ColorPixel](height, width)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val color:Color = new Color(pngImage.getRGB(x,y))
        matrix(y)(x) = new ColorPixel(color)
      }
    }

    new ImageMatrix[ColorPixel](matrix.map(_.toList).toList)

  }
}
