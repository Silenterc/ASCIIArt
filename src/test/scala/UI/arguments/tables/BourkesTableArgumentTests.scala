package UI.arguments.tables

import models.tables.BourkesTable
import org.scalatest.FunSuite

class BourkesTableArgumentTests extends FunSuite {

  test("getResult() should create a BourkesTable") {
    val tableArg = new BourkesTableArgument
    val bourkesTable = tableArg.getResult
    assert(bourkesTable.isInstanceOf[BourkesTable])
  }
}
