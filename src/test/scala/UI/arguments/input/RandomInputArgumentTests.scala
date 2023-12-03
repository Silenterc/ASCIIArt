package UI.arguments.input

import loaders.MyRandomImageLoader
import org.scalatest.FunSuite

class RandomInputArgumentTests extends FunSuite{
  test("getResult() should create a MyRandomImageLoader") {
    val inputArg = new RandomInputArgument
    val loader = inputArg.getResult
    assert(loader.isInstanceOf[MyRandomImageLoader])
  }
}
