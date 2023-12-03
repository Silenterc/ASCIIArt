package UI.arguments.tables

import models.tables.MyNonLinearTable
import org.scalatest.FunSuite

class MyNonLinearTableArgumentTests extends FunSuite {

  test("getResult() should create a MyNonLinearTable") {
    val tableArg = new MyNonLinearTableArgument
    val myNonLinearTable = tableArg.getResult
    assert(myNonLinearTable.isInstanceOf[MyNonLinearTable])
  }
}
