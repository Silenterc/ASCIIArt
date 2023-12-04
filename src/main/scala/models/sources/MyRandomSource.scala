package models.sources
import models.matrices.ImageMatrix
import models.pixels.ColorPixel

import java.awt.Color
import scala.util.Random
/**
 * Random source of Image which I pioneered
 */
class MyRandomSource extends RandomSource{
  /**
   * Creates a ColorPixel Matrix by making random colors for each pixel
   * @return random ColorPixel matrix
   */
  override def getSource: ImageMatrix[ColorPixel] = {
    val height = Random.between(1, maxHeight + 1)
    val width = Random.between(1, maxWidth + 1)
    val currMatrix = Array.ofDim[ColorPixel](height, width)
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val randomColor = new Color(Random.nextInt(256),Random.nextInt(256),Random.nextInt(256))
        currMatrix(y)(x) = ColorPixel(randomColor)
      }
    }
    new ImageMatrix[ColorPixel](currMatrix.map(_.toList).toList)
  }
}
