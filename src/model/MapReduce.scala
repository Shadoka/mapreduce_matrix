package model

import scala.collection.mutable.HashMap
import scala.collection.immutable.Map

abstract class MapReduce[MapIn, Key, Value] {
  
  def run (sequence: List[MapIn]): Map[Key, Value] = {
    reduce(group(map(sequence)))
  }
  
  def map (sequence: List[MapIn]): List[(Key, Value)]
  
  def reduce (groups: Map[Key, List[Value]]): Map[Key, Value]
  
  def group (pairs: List[(Key, Value)]): Map[Key, List[Value]] = {
    val result = HashMap[Key, List[Value]]()
    
    for ((key, value) <- pairs) {
      if (result.contains(key)) result.+=(key -> result.get(key).get.+:(value))
      else result.+=(key -> List(value))
    }
    result.toMap
  }
  
}