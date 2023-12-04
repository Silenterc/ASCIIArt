package models.sources

/**
 * Some source of Image
 * @tparam T the source type
 */
trait ImageSource[T] {
  def getSource: T
}
