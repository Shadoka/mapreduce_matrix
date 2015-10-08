package algorithms

import scala.collection.mutable.HashMap
import scala.collection.mutable.ListBuffer
import model.MapReduce

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
    	if (result.contains(key)) {
    	  result.+=(key -> result.get(key).get.+(values.sum))
    	} else {
    	  result.+=(key -> values.sum)
    	}
    }
    result.toMap
  }
  
}