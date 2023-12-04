package exporters

import models.matrices.ImageMatrix
import models.pixels.Pixel

import java.io.OutputStream

/**
 * Exporter of an ImageMatrix to some output Stream
 * @param outputStream some output Stream
 */
class StreamMatrixExporter(outputStream : OutputStream) extends ImageMatrixExporter {
  private var closed = false

  /**
   * Exports mat to the stream given in the constructor
   * @param mat ImageMatrix to be exported
   */
  protected def exportToStream(mat: ImageMatrix[Pixel[_]]): Unit = {

    if (closed)
      throw new Exception("The stream is already closed")
    mat.foreach{ row =>
                     row.foreach(p => outputStream.write((p.toString + " ").getBytes()))
                     outputStream.write("\n".getBytes())
    }
    outputStream.flush()
  }

  /**
   * Closes the stream
   */
  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }

  override def export(item: ImageMatrix[Pixel[_]]): Unit = exportToStream(item)

}
