package rdfKernels
	
import org.apache.spark._
import org.apache.spark.graphx._
import java.lang.System
import java.net.URI
import net.sansa_stack.rdf.spark.io.NTripleReader
import org.apache.spark.sql.SparkSession
import scala.collection.mutable
import net.sansa_stack.rdf.spark.graph._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx._
import org.apache.spark.graphx.{Graph, VertexRDD}
import org.apache.spark.graphx.util.GraphGenerators


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
    val triplesRDD = NTripleReader.load(spark, URI.create("/home/d/Desktop/data.nt"))
    triplesRDD.take(10).foreach(println(_))
    val graph = LoadGraph(triplesRDD)
    val init_paths = graph.mapVertices((id, attr) => (0, 0))
    var rec_paths = init_paths.mapVertices((id, attr) => (1,0))
    var paths: VertexRDD[(Int, Int)] = rec_paths.aggregateMessages[(Int, Int)]( predic => {predic.sendToDst(predic.dstAttr._1,predic.srcAttr._1)},(a, b) => (b._1,a._2 + b._2))
    var numpaths = paths.map(_._2._2).reduce(_ + _)
    println(numpaths)
    for( i <- 2 to 10){
      rec_paths =  init_paths.joinVertices(paths)((id, old_attr, new_attr) => (new_attr._2, 0))
      paths = rec_paths.aggregateMessages[(Int, Int)]( predic => {if(predic.srcAttr._1>0) predic.sendToDst(predic.dstAttr._1,predic.srcAttr._1)},(a, b) => (b._1,a._2 + b._2))
      numpaths = paths.map(_._2._2).reduce(_ + _)
      println(numpaths)
    }
    
    // Try to use own defined functions
    //paths_length_l.collect.foreach(println(_))    //path_kernel.test()
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