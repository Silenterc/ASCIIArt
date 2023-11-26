package exporters

import models.matrices.ImageMatrix
import models.pixels.Pixel

trait ImageMatrixExporter extends Exporter[ImageMatrix[Pixel[_]]] {

}
