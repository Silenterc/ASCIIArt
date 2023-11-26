package models.tables

trait Table {
  protected var table: Array[Char] = new Array[Char](256)
  def getChar(index: Int): Char

  def getChar(index: Double): Char

  protected def createTable(): Unit
}
