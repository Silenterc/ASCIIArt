package models.sources

import models.matrices.ImageMatrix
import models.pixels.RGBPixel

trait RandomSource extends ImageSource[ImageMatrix[RGBPixel]]{

}
