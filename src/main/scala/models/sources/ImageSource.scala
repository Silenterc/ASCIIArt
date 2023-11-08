package models.sources

trait ImageSource[T] {
  def getSource(): T
}
