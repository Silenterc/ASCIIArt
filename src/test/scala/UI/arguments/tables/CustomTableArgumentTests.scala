package UI.arguments.tables

import models.tables.LinearTable
import org.scalatest.FunSuite

class CustomTableArgumentTests extends FunSuite{
  test("getResult() should create a LinearTable with custom characters #._") {
    val tableArg = new CustomTableArgument("#._")
    val linearTable = tableArg.getResult
    assert(linearTable.isInstanceOf[LinearTable])
    val iter = 256/3
    for (i <- 0 to iter) {
      assert(linearTable.getChar(i) equals '#')
    }
    val iter2 = iter * 2
    for (i <- iter + 1 to iter2) {
      assert(linearTable.getChar(i) equals '.')
    }

    for (i <- iter2 + 1 to 255) {
      assert(linearTable.getChar(i) equals '_')
    }
  }

  test("getResult() should create a LinearTable with custom characters #.") {
    val tableArg = new CustomTableArgument("#.")
    val linearTable = tableArg.getResult
    assert(linearTable.isInstanceOf[LinearTable])
    val iter = 256 / 2
    for (i <- 0 until iter) {
      assert(linearTable.getChar(i) equals '#')
    }

    for (i <- iter  to 255) {
      assert(linearTable.getChar(i) equals '.')
    }
  }
}
