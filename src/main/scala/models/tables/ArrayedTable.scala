package models.tables

trait ArrayedTable extends Table{
  protected var table: Array[Char] = new Array[Char](256)
  protected def createTable(): Unit
}
