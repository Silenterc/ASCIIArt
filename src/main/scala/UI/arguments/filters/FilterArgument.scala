package UI.arguments.filters

import UI.arguments.Argument
import filters.Filter

trait FilterArgument[+T <: Filter[_]] extends Argument[T] {

}
