package UI.arguments.output

import UI.arguments.Argument
import exporters.Exporter

trait OutputArgument[T <: Exporter[_]] extends Argument[T] {

}
