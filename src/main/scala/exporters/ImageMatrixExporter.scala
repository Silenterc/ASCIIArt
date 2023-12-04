package exporters

import models.matrices.ImageMatrix
import models.pixels.Pixel

/**
 * Exporter of an ImageMatrix somewhere
 */
trait ImageMatrixExporter extends Exporter[ImageMatrix[Pixel[_]]] {

}
