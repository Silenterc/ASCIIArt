package UI.arguments.filters

import filters.Greyscale.InvertFilter
import org.scalatest.FunSuite
import org.scalatest.Matchers.{be, convertToAnyShouldWrapper}


class InvertFilterArgumentTests extends FunSuite{
  test("getResult should return a new InvertFilter") {
    val invertArg = new InvertFilterArgument
    val invertFilter:InvertFilter = invertArg.getResult
    invertFilter match {
      case f: InvertFilter => true
      case _ => false
    }
  }
}
