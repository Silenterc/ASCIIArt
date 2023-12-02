package UI.arguments.tables

import models.tables.LinearTable

class CustomTableArgument(chars: String) extends TableArgument[LinearTable] {
  override def getResult: LinearTable = {
    new LinearTable(chars)

  }
}
