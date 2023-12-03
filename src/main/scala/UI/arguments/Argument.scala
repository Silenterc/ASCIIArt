package UI.arguments

trait Argument[+T] {
  def getResult :T
}
