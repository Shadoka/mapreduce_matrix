package model

class Vector[T] (size: Int, gen: (Int) => T) {
  val items = for (index <- 0 until size) yield gen(index)

  def get (index: Int): T = {
    items(index)
  }
}