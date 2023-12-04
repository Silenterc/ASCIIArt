package exporters

import java.io.{File, FileOutputStream}

/**
 * Exporter of an ImageMatrix to a file
 * @param file output File
 */
class FileOutputMatrixExporter(file: File) extends StreamMatrixExporter(new FileOutputStream(file)) {

}
