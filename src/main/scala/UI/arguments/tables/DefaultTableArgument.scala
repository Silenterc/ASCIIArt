package UI.arguments.tables

import models.tables.DefaultTable

class DefaultTableArgument extends TableArgument[DefaultTable] {
  override def getResult: DefaultTable = {
    new DefaultTable
  }
}
