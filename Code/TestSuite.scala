package rdfKernels

import java.lang.System
import java.net.URI
import net.sansa_stack.rdf.spark.io.NTripleReader
import org.apache.spark.sql.SparkSession
import scala.collection.mutable
import net.sansa_stack.rdf.spark.graph._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx._


object TestSuite {

  /**
   * @param args
   */
  def main(args: Array[String]) {
    parser.parse(args, Config()) match {
      case Some(config) =>
        run(config.in)
      case None =>
        println(parser.usage)
    }
  }

  def run(input: String): Unit = {

    val spark = SparkSession.builder
      .appName(s"Test Suite Graph Kernels  $input")
      .master("local[*]")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .getOrCreate()
    val time = System.nanoTime
    println("======================================")
    println("|   Test suite for Graph Kernels     |")
    println("======================================")
    //read triples
    val triplesRDD = NTripleReader.load(spark, URI.create("/home/d/Desktop/intesect_small.nt"))
    triplesRDD.take(3).foreach(println(_))
    // convert to graphX
    val graph = LoadGraph(triplesRDD)
   
    
    val triplesRDD2 = NTripleReader.load(spark, URI.create("/home/d/Desktop/intesect_big.nt"))
    val graph2 = LoadGraph(triplesRDD2)

    // convert to graphX
    // val intesect = graph.subgraph(epred: (EdgeTriplet[VD, ED])  Boolean = x => true, vpred: (VertexId, VD) ⇒ Boolean = (v, d) => true): Graph[VD, ED]
    
    // collect neighbors
    for (i <- 1 to 1)
    {
      val Neighbors: VertexRDD[Array[VertexId]] = graph.ops.collectNeighborIds(EdgeDirection.Out);
      val Neighbors2: VertexRDD[Array[VertexId]] = graph2.ops.collectNeighborIds(EdgeDirection.Out);     

    }
    // Try to use own defined functions
    val path_kernel = new rdfKernels.kernelFunctions()
    //path_kernel.test()
    println(System.nanoTime - time)
    spark.stop
  }

  case class Config(in: String = "")

  val parser = new scopt.OptionParser[Config]("Test Suite") {

    head("Test suite")

    opt[String]('i', "input").required().valueName("<path>").
      action((x, c) => c.copy(in = x)).
      text("path to file that contains the data (in N-Triples format)")
      
    help("help").text("prints this usage text")
  }
}