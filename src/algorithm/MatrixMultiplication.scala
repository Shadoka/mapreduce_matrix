package algorithm

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import model.MapReduce

/**
 * Multiplies every row of a matrix with a vector of the same length. The input
 * is provided as list of tripels: 
 *  row: A row index of the matrix
 *  vectorValue: The value in the vector at index row
 *  matrixValue: The value in the matrix at the row index row and a column index 
 */
class MatrixMultiplication extends MapReduce[(Int, Int, Int), Int, Int] {

  override def map (matrixValues: List[(Int, Int, Int)]): List[(Int, Int)] = {
	val result = ListBuffer[(Int, Int)]()
	
	for ((row, vVal, mVal) <- matrixValues) {
	  result.+=((row, vVal * mVal))
	}
	result.toList
  }
  
  override def reduce (groups: Map[Int, List[Int]]): Map[Int, Int] = {
    val result = HashMap[Int, Int]()
    
    for ((key, values) <- groups) {
    	result.+=(key -> result.getOrElse(key, 0).+(values.sum))
    }
    result.toMap
  }
  
}