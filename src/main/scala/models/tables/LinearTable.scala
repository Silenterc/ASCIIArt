package models.tables

class LinearTable(strTable: String) extends ArrayedTable {
  checkInput()
  createTable()

  private def checkInput(): Unit = {
    if (strTable.isEmpty || strTable.length > 256) {
      throw new Exception("Invalid Table String")
    }
  }

  override protected def createTable(): Unit = {
    val strLen = strTable.length
    for (i <- table.indices) {
      val scaledIndex = (i * strLen) / 256
      table(i) = strTable(scaledIndex)
    }
  }

  override def getChar(index: Int): Char = {
    table(index)
  }

  override def getChar(index: Double): Char = {
    table(index.toInt)
  }

}
