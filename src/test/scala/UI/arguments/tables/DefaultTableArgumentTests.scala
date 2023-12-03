package UI.arguments.tables

import models.tables.DefaultTable
import org.scalatest.FunSuite

class DefaultTableArgumentTests extends FunSuite {

  test("getResult() should create a DefaultTable") {
    val tableArg = new DefaultTableArgument
    val defaultTable = tableArg.getResult
    assert(defaultTable.isInstanceOf[DefaultTable])
  }
}
