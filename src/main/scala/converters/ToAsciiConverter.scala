package converters

import models.matrices.ImageMatrix
import models.pixels.{CharPixel, GreyscalePixel}

trait ToAsciiConverter extends Converter[ImageMatrix[GreyscalePixel], ImageMatrix[CharPixel]]{

}
