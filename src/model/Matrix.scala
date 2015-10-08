package model

import scala.collection.mutable.ListBuffer

class Matrix[T] (val size: Int, val gen: (Int, Int) => T) {
  val rows = ListBuffer[ListBuffer[T]]();
 
  for (row <- 0 until size) {
    val rowValues = for (col <- 0 until size) yield gen(row, col)
    rows.+=(rowValues.to[ListBuffer])
  }
  
  def get (row: Int, col: Int): T = {
    rows(row)(col)
  }
  
  def getSize = size
  
}