package UI.arguments.tables

import models.tables.BourkesTable

class BourkesTableArgument extends TableArgument[BourkesTable] {
  override def getResult: BourkesTable = {
    new BourkesTable
  }
}
