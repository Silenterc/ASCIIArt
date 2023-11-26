package exporters

import java.io.{File, FileOutputStream}

class FileOutputMatrixExporter(file: File) extends StreamMatrixExporter(new FileOutputStream(file)) {

}
