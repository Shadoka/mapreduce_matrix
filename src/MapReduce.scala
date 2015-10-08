import scala.collection.mutable.ListBuffer
import scala.collection.mutable.HashMap

class MapReduce {

  def run (matrix: Matrix[Int], vector: Vector[Int]): Map[Int, Int] = {
    reduce(group(map(matrix, vector)))
  }
  
  def map (matrix: Matrix[Int], vector: Vector[Int]): List[(Int, Int)] = {
	val result = ListBuffer[(Int, Int)]()
	
    for (row <- 0 until matrix.getSize) {
    	for (col <- 0 until matrix.getSize) {
    	  result.+=((row, matrix.get(row, col) * vector.get(row)))
    	}
    }
	result.toList
  }
  
  def group (pairs: List[(Int, Int)]): Map[Int, List[Int]] = {
    val result = HashMap[Int, List[Int]]()
    
    for ((key, value) <- pairs) {
      if (result.contains(key)) {
        result.+=(key -> result.get(key).get.+:(value))
      } else {
        result.+=(key -> List(value))
      }
    }
    result.toMap
  }
  
  def reduce (groups: Map[Int, List[Int]]): Map[Int, Int] = {
    val result = HashMap[Int, Int]()
    
    for ((key, values) <- groups) {
    	if (result.contains(key)) {
    	  result.+=(key -> result.get(key).get.+(values.sum))
    	} else {
    	  result.+=(key -> values.sum)
    	}
    }
    result.toMap
  }
  
}