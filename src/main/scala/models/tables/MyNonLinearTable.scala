package models.tables

class MyNonLinearTable extends NonLinearTable {
  createTable()
  override protected def createTable(): Unit = {
    for(i <- table.indices) {
      if (i > 240) {
        table(i) = '.'
      } else if (i > 235) {
        table(i) = '^'
      } else if (i > 230) {
        table(i) = 'i'
      } else if (i > 210) {
        table(i) = '1'
      } else if (i > 187) {
        table(i) = 't'
      } else if (i > 150) {
        table(i) = 'z'
      } else if (i > 50) {
        table(i) = 'X'
      } else {
        table(i) = 'B'
      }
    }

  }

  override def getChar(index: Double): Char = {
    table(index.toInt)
  }

  override def getChar(index: Int): Char = {
    table(index)
  }
}
