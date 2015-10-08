package src.algorithms

import algorithms.MatrixMultiplication
import model.Matrix
import model.Vector
import scala.collection.mutable.ListBuffer

object Main {

  def main(args: Array[String]) {
	  val process = new MatrixMultiplication()
	  val matrix = new Matrix(100, (col, row) => 1)
	  val vector = new Vector(100, (index) => index % 2)
	  val result = process.run(toList(matrix, vector))
	  
	  println(result.toSeq.sorted)
  }
  
  def toList (matrix: Matrix[Int], vector: Vector[Int]): List[(Int, Int, Int)] = {
    val result = ListBuffer[(Int, Int, Int)]()
    
    for (row <- 0 until matrix.getSize) {
    	for (col <- 0 until matrix.getSize) {
    	  result.+=((row, vector.get(row), matrix.get(row, col)))
    	}
    }
    result.toList		
  }
  
}