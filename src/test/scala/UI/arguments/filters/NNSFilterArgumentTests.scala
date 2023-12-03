package UI.arguments.filters

import org.scalatest.FunSuite

import scala.math.sqrt

class NNSFilterArgumentTests extends FunSuite{
  test("getResult() should create a NearestNeighScaleFilter with valid input = 1.0") {
    val scaleArg = new NearestNeighScaleFilterArgument("1.0")
    val scaleFilter = scaleArg.getResult
    assert(scaleFilter.dimensionScale == sqrt(1.0))
  }

  test("getResult() should create a NearestNeighScaleFilter with valid input = 1") {
    val scaleArg = new NearestNeighScaleFilterArgument("1")
    val scaleFilter = scaleArg.getResult
    assert(scaleFilter.dimensionScale == sqrt(1))
  }

  test("getResult() should create a NearestNeighScaleFilter with valid input = 2") {
    val scaleArg = new NearestNeighScaleFilterArgument("2")
    val scaleFilter = scaleArg.getResult
    assert(scaleFilter.dimensionScale == sqrt(2))
  }

  test("getResult() should create a NearestNeighScaleFilter with valid input = 4") {
    val scaleArg = new NearestNeighScaleFilterArgument("4")
    val scaleFilter = scaleArg.getResult
    assert(scaleFilter.dimensionScale == 2)
  }

  test("getResult() should create a NearestNeighScaleFilter with valid input = 4.20") {
    val scaleArg = new NearestNeighScaleFilterArgument("4.20")
    val scaleFilter = scaleArg.getResult
    assert(scaleFilter.dimensionScale == sqrt(4.20))
  }

  test("getResult() should create a NearestNeighScaleFilter with valid input = 0.000001") {
    val scaleArg = new NearestNeighScaleFilterArgument("0.000001")
    val scaleFilter = scaleArg.getResult
    assert(scaleFilter.dimensionScale == sqrt(0.000001))
  }

  test("getResult() should throw for input 0") {
    val exc = intercept[IllegalArgumentException] {
      new NearestNeighScaleFilterArgument("0").getResult
    }
    assert(exc.getMessage equals "Wrong Scale filter value - 0.")
  }

  test("getResult() should throw for word input") {
    val exc = intercept[IllegalArgumentException] {
      new NearestNeighScaleFilterArgument("invalid").getResult
    }
    assert(exc.getMessage equals "Wrong Scale filter value - invalid.")
  }

  test("getResult() should throw for letter in numeric input") {
    val exc = intercept[IllegalArgumentException] {
      new NearestNeighScaleFilterArgument("1I0").getResult
    }
    assert(exc.getMessage equals "Wrong Scale filter value - 1I0.")
  }

  test("getResult() should throw for negative input = -2.0") {
    val exc = intercept[IllegalArgumentException] {
      new NearestNeighScaleFilterArgument("-2.0").getResult
    }
    assert(exc.getMessage equals "Wrong Scale filter value - -2.0.")
  }

  test("NearestNeighScaleFilterArgument should throw for empty input") {
    val exc = intercept[IllegalArgumentException] {
      new NearestNeighScaleFilterArgument("").getResult
    }
    assert(exc.getMessage equals "Wrong Scale filter value - .")
  }
}
