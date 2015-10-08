package demo

import algorithm.WordCount
import scala.io.Source

object WordMapReduce {

  val file = "D:\\workspace_scala\\map-reduce\\resources\\text.txt"
  
  def main(args: Array[String]) {
	  val text = Source.fromFile(file).getLines().toList
    val process = new WordCount()
	  val result = process.run(text)
	  
    print(result)
  }
 
  def print (result: Map[String, Int]) {
     for ((key, value) <- result.toSeq.sortBy(_._2)) println(key + " -> " + value)
  }
  
}