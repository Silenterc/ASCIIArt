package exporters

import models.matrices.ImageMatrix
import models.pixels.{CharPixel, ColorPixel, GreyscalePixel}
import org.scalatest.FunSuite

import java.awt.Color
import java.io.ByteArrayOutputStream

class StreamMatrixExporterTests extends FunSuite{
  test("Export ImageMatrix[ColorPixel]") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamMatrixExporter(stream)
    val matrix = new ImageMatrix(List(
      List(ColorPixel(Color.black), ColorPixel(Color.white)),
      List(ColorPixel(Color.red), ColorPixel(Color.cyan))
    ))
    exporter.export(matrix)
    exporter.close()

    val expected = "[0,0,0] [255,255,255] \n[255,0,0] [0,255,255] \n"

    assert(stream.toString("UTF-8") equals expected)
    assert(stream.toString("UTF-8") equals matrix.toString)
  }

  test("Export ImageMatrix[GreyscalePixel]") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamMatrixExporter(stream)
    val matrix = new ImageMatrix(List(
      List(GreyscalePixel(1), GreyscalePixel(2)),
      List(GreyscalePixel(3), GreyscalePixel(4))
    ))
    exporter.export(matrix)
    exporter.close()
    val expected = "1 2 \n3 4 \n"

    assert(stream.toString("UTF-8") equals expected)
    assert(stream.toString("UTF-8") equals matrix.toString)
  }

  test("Export ImageMatrix[CharPixel]") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamMatrixExporter(stream)
    val matrix = new ImageMatrix(List(List(CharPixel('l'), CharPixel('o'), CharPixel('l')),
                                      List(CharPixel('4'), CharPixel('2'), CharPixel('0'))))
    exporter.export(matrix)
    exporter.close()
    val expected = "l o l \n4 2 0 \n"

    assert(stream.toString("UTF-8") equals expected)
    assert(stream.toString("UTF-8") equals matrix.toString)
  }

  test("Export empty matrix") {
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamMatrixExporter(stream)
    val matrix = new ImageMatrix(List.empty[List[CharPixel]])
    exporter.export(matrix)
    exporter.close()
    val expected = ""

    assert(stream.toString("UTF-8") equals expected)
    assert(stream.toString("UTF-8") equals matrix.toString)
  }
}
