package models.sources

import org.scalatest.FunSuite

class MyRandomSourceTests extends FunSuite{

  test("Get a usable Random Source, 25 iterations") {
    val tested = new MyRandomSource
    for (i <- 0 until 25) {
      val matrix = tested.getSource()
      assert(matrix.size() <= tested.maxHeight)
      assert(matrix.forall(list => list.size <= tested.maxWidth))
      val supposedWidth = matrix.getMatrix.head.size
      assert(matrix.forall(list => list.size == supposedWidth))
    }
  }

}
