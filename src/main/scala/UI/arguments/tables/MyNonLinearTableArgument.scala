package UI.arguments.tables

import models.tables.MyNonLinearTable

class MyNonLinearTableArgument extends TableArgument[MyNonLinearTable] {
  override def getResult: MyNonLinearTable = {
    new MyNonLinearTable
  }
}
