package loaders

import models.sources.{MyRandomSource, RandomSource}


trait RandomImageLoader[T <: RandomSource] extends Loader[T]{

}
