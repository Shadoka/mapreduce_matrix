package model

import scala.collection.mutable.HashMap
import scala.collection.immutable.Map

/**
 * A MapReduce Algorithm calls on every input the map function and feeds
 * the grouped output to the reduce function.
 * 
 * TODO: The map and reduce functions are called with chunks of the input 
 * in parallel. The reduce tasks will not be started before the map tasks
 * have finished. 
 */
abstract class MapReduce[MapIn, Key, Value] {
  
  /**
   * Starts the algorithm with the input <sequence>. Executes:
   *  The map function in parallel, each with a chunk of <sequence>
   *  The group function after the map functions have finished.
   *  The reduce functions in parallel, each parallel
   *  Returns the result of reduced as Key -> Value Map.
   */
  def run (sequence: List[MapIn]): Map[Key, Value] = {
    val splits = 10
    val length = sequence.size / splits
    
    reduce(group(map(sequence)))
  }
  
  /**
   * Transforms every value of <sequence> to a pair of type (Key, Value)
   * and returns them in as a List.
   */
  def map (sequence: List[MapIn]): List[(Key, Value)]
  
  /**
   * Transforms every Value of a Key in <groups> to a single value. The 
   * result is a Map from Key to the aggregated value.
   */
  def reduce (groups: Map[Key, List[Value]]): Map[Key, Value]
  
  /**
   * Returns a Map from the Key of a Tupel in <pairs> to a List of values.
   * Every value in the List of a Key had the same Key in the Tupel of <pairs>.
   */
  def group (pairs: List[(Key, Value)]): Map[Key, List[Value]] = {
    val result = HashMap[Key, List[Value]]()
    
    for ((key, value) <- pairs) {
      result.+=(key -> result.getOrElse(key, List[Value]()).+:(value))
    }
    result.toMap
  }
  
}