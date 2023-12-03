package UI.arguments.filters

import filters.Greyscale.BrightnessFilter
import org.scalatest.FunSuite

class BrightnessFilterArgumentTests extends FunSuite{
  test("getResult() should create a BrightnessFilter with valid input = 50") {
    val brightnessArg = new BrightnessFilterArgument("50")
    val brightnessFilter = brightnessArg.getResult
    assert(brightnessFilter.change == 50)
  }

  test("getResult() should create a BrightnessFilter with valid input = +50") {
    val brightnessArg = new BrightnessFilterArgument("+50")
    val brightnessFilter = brightnessArg.getResult
    assert(brightnessFilter.change == 50)
  }

  test("getResult() should create a BrightnessFilter with valid input = -50") {
    val brightnessArg = new BrightnessFilterArgument("-50")
    val brightnessFilter = brightnessArg.getResult
    assert(brightnessFilter.change == -50)
  }

  test("getResult() should create a BrightnessFilter with valid input = 0") {
    val brightnessArg = new BrightnessFilterArgument("0")
    val brightnessFilter = brightnessArg.getResult
    assert(brightnessFilter.change == 0)
  }

  test("getResult() should create a BrightnessFilter with valid input = -0") {
    val brightnessArg = new BrightnessFilterArgument("-0")
    val brightnessFilter = brightnessArg.getResult
    assert(brightnessFilter.change == 0)
  }

  test("BrightnessFilterArgument should throw for word as input") {
    val exc = intercept[IllegalArgumentException] {
      new BrightnessFilterArgument("invalid").getResult
    }
    assert(exc.getMessage equals "Wrong Brightness filter value - invalid.")
  }

  test("BrightnessFilterArgument should throw for letter in input") {
    val exc = intercept[IllegalArgumentException] {
      new BrightnessFilterArgument("1I0").getResult
    }
    assert(exc.getMessage equals "Wrong Brightness filter value - 1I0.")
  }

  test("BrightnessFilterArgument should throw for empty input") {
    val exc = intercept[IllegalArgumentException] {
      new BrightnessFilterArgument("").getResult
    }
    assert(exc.getMessage equals "Wrong Brightness filter value - .")
  }
}
