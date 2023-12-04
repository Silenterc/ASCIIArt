package exporters

/**
 * Exporter of T
 * --The exporters were very much inspired by the ones we did during Labs
 * @tparam T something
 */
trait Exporter[T] {
  /**
   * Exports something somewhere
   *
   * @param item The item to export
   */
  def export(item: T): Unit

}
