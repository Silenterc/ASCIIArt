package UI.arguments.filters

import UI.arguments.Argument
import filters.Greyscale.InvertFilter

class InvertFilterArgument extends FilterArgument[InvertFilter]{
  override def getResult: InvertFilter = {
    new InvertFilter()
  }
}
