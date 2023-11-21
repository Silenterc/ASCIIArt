package models

class LinearTable(strTable: String = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. ") {
  private var table: Array[Char] = new Array[Char](256)
  checkInput()
  createTable()

  private def checkInput(): Unit = {
    if (strTable.isEmpty || strTable.length > 255) {
      throw new Exception("Invalid Table String")
    }
  }

  private def createTable(): Unit = {
    val strLen = strTable.length
    for (i <- table.indices) {
      val scaledIndex = (i * strLen) / 256
      table(i) = strTable(scaledIndex)
    }
  }

  def getChar(index: Int): Char = {
    table(index)
  }

  def getChar(index: Double): Char = {
    table(index.toInt)
  }

}
