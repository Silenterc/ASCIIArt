package models.tables

trait Table {
  def getChar(index: Int): Char

  def getChar(index: Double): Char
}
