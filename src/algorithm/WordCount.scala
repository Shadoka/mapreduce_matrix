package algorithms

import model.MapReduce
import scala.collection.mutable.HashMap

/**
 * Counts every word in a given text and returns a Map word -> count.
 */
class WordCount extends MapReduce[String, String, Int] {

    val splitChars = Array(
      '\n', '\r', '\t', ' ', '"', '\'', ',', '.', '!', '?', '-', '_',
      '+', '*', '/', '&', '%', '$', '§', '^', '(', ')', '#', ':', ';',
      '“', '”', '∞', '√', '✷', '' , '’'
  )
  
  override def map(lines: List[String]): List[(String, Int)] = {
    lines.flatMap { line => line.split(splitChars)
      .filter { word => word.length > 0 }
      .map { word => (word, 1) } }  
  }
 
  override def reduce(pairs: Map[String, List[Int]]): Map[String, Int] = {
     val result = HashMap[String, Int]()
     
     for ((key, values) <- pairs) {
       result.+=(key -> result.getOrElse(key, 0).+(values.sum))
     }
     result.toMap
  }
    
}