package UI.arguments.filters

import UI.arguments.Argument
import filters.Greyscale.{NearestNeighScaleFilter, ScaleFilter}

class NearestNeighScaleFilterArgument(value:String) extends ScaleFilterArgument {
  override def getResult: ScaleFilter = {
    val numValue = value.toDoubleOption
    if (numValue.isEmpty || numValue.get <= 0) {
      throw new IllegalArgumentException(s"Wrong Scale filter value - $value.")
    }
    new NearestNeighScaleFilter(numValue.get)
  }
}
