import scala.collection.mutable.ListBuffer

class Matrix[T] (size: Int, gen: () => T) {

  val rows = ListBuffer[ListBuffer[T]]();
 
  for (row <- 0 until size) {
    
  }
  
  def put (row: Int, col: Int, value: T) {
    rows(row).insert(col, value)
  }
  
  def get (row: Int, col: Int): T = {
    rows(row)(col)
  }
  
}