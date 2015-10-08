package model

import scala.collection.mutable.ListBuffer

/**
 * Dummy Matrix
 */
class Matrix[T] (val size: Int, val gen: (Int, Int) => T) {
  val rows = ListBuffer[ListBuffer[T]]();
 
  for (row <- 0 until size) {
    val rowValues = for (col <- 0 until size) yield gen(row, col)
    rows.+=(rowValues.to[ListBuffer])
  }
  
  def get (row: Int, col: Int): T = {
    rows(row)(col)
  }
  
   /**
   * Create a Tupel3 of Integers for every cell in rows, the belonging
   * row and the value of <Vector> at position row, e.g.:
   * 
   *    matrix  vector     list
   *     0  1    0  1
   * 0  [3][1]  [1][0]  => [(0, 1, 3), (0, 1, 1), (1, 0, 4), (1, 0, 0)]
   * 1  [4][0]
   * 
   * Returns all Tupel3 in a List.
   */
  def toList (vector: Vector[T]): List[(Int, T, T)] = {
    val result = ListBuffer[(Int, T, T)]()
    
    for (row <- 0 until getSize) {
      for (col <- 0 until getSize) {
        result.+=((row, vector.get(row), this.get(row, col)))
      }
    }
    result.toList
  }
  
  def getSize = size
}