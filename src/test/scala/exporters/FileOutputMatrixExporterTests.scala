package exporters

import models.matrices.ImageMatrix
import models.pixels.{CharPixel, ColorPixel, GreyscalePixel}
import org.scalatest.FunSuite

import java.awt.Color
import java.io.File
import scala.io.Source

class FileOutputMatrixExporterTests extends FunSuite {
    test("Export ImageMatrix[ColorPixel]") {
      val path = "src/test/pics/output"
      val exporter = new FileOutputMatrixExporter(new File(path))
      val matrix = new ImageMatrix(List(
        List(ColorPixel(Color.black), ColorPixel(Color.white)),
        List(ColorPixel(Color.red), ColorPixel(Color.cyan))
      ))
      exporter.export(matrix)
      exporter.close()

      val source = Source.fromFile(path)
      val text = source.mkString
      source.close()
      val expected = "[0,0,0] [255,255,255] \n[255,0,0] [0,255,255] \n"

      assert (text equals expected)
      assert(text equals matrix.toString)
      val del = new File(path)
      del.delete()
    }

  test("Export ImageMatrix[GreyscalePixel]") {
    val path = "src/test/pics/output"
    val exporter = new FileOutputMatrixExporter(new File(path))
    val matrix = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4))
    ))
    exporter.export(matrix)
    exporter.close()

    val source = Source.fromFile(path)
    val text = source.mkString
    source.close()
    val expected = "1 2 \n3 4 \n"

    assert(text equals expected)
    assert(text equals matrix.toString)
    val del = new File(path)
    del.delete()
  }

  test("Export ImageMatrix[CharPixel]") {
    val path = "src/test/pics/output"
    val exporter = new FileOutputMatrixExporter(new File(path))
    val matrix = new ImageMatrix(List(List(CharPixel('l'), CharPixel('o'), CharPixel('l')),
                                 List(CharPixel('4'), CharPixel('2'), CharPixel('0'))))
    exporter.export(matrix)
    exporter.close()

    val source = Source.fromFile(path)
    val text = source.mkString
    source.close()
    val expected = "l o l \n4 2 0 \n"

    assert(text equals expected)
    assert(text equals matrix.toString)
    val del = new File(path)
    del.delete()
  }

  test("Export empty matrix") {
    val path = "src/test/pics/output"
    val exporter = new FileOutputMatrixExporter(new File(path))
    val matrix = new ImageMatrix(List.empty[List[CharPixel]])
    exporter.export(matrix)
    exporter.close()

    val source = Source.fromFile(path)
    val text = source.mkString
    source.close()
    val expected = ""

    assert(text equals expected)
    assert(text equals matrix.toString)
    val del = new File(path)
    del.delete()
  }

  }
