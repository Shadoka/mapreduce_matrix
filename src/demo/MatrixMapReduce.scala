package demo

import scala.collection.mutable.ListBuffer
import model.Matrix
import model.Vector
import algorithm.MatrixMultiplication

object MatrixMapReduce {

  def main(args: Array[String]) {
	  val process = new MatrixMultiplication()
	  val matrix = new Matrix(100, (col, row) => 1)
	  val vector = new Vector(100, (index) => index % 2)
	  val result = process.run(matrix.toList(vector))
	  
    print(result)
  }
  
  def print (result: Map[Int, Int]) {
    for ((i, v) <- result.toSeq.sortBy(_._1)) println(i + " -> " + v)    
  }
  
}