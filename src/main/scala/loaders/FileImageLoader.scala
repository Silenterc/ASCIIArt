package loaders
import models.matrices.ImageMatrix
import models.pixels.ColorPixel
import models.sources.{FileSource, ImageSource}

trait FileImageLoader[T <: FileSource] extends Loader[T]{

}
