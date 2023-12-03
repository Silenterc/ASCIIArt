package UI.arguments.tables

import UI.arguments.Argument
import models.tables.Table

trait TableArgument[+T <: Table] extends Argument[T]{

}
