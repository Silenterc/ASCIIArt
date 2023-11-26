package exporters

import models.matrices.ImageMatrix
import models.pixels.Pixel

import java.io.OutputStream

class StreamMatrixExporter(outputStream : OutputStream) extends ImageMatrixExporter {
  private var closed = false

  protected def exportToStream(mat: ImageMatrix[Pixel[_]]): Unit = {

    if (closed)
      throw new Exception("The stream is already closed")
    mat.foreach{ row =>
                     row.foreach(p => outputStream.write((p.toString + " ").getBytes()))
                     outputStream.write("\n".getBytes())
    }
    outputStream.flush()
  }

  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }

  override def export(item: ImageMatrix[Pixel[_]]): Unit = exportToStream(item)

}
