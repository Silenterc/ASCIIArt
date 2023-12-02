package UI.arguments.filters

import UI.arguments.Argument
import filters.Greyscale.BrightnessFilter

class BrightnessFilterArgument(value:String) extends FilterArgument[BrightnessFilter] {
  override def getResult: BrightnessFilter = {
    val numValue = value.toIntOption
    if(numValue.isEmpty) {
      throw new IllegalArgumentException(s"Wrong Brightness filter value - $value.")
    }
    new BrightnessFilter(numValue.get)
  }
}
