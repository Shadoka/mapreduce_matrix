object Main {

  def main(args: Array[String]) {
	  val process = new MapReduce()
	  val matrix = new Matrix(100, (col, row) => 1)
	  val vector = new Vector(100, (index) => index % 2)
	  val result = process.run(matrix, vector)
	  
	  println(result.toSeq.sorted)
  }
  
}