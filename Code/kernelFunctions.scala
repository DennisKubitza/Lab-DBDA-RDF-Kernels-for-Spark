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

class kernelFunctions {
  
  def test(){
    
    //val triplesRDD = NTripleReader.load(spark, URI.create("/home/d/Desktop/infobox_en/data.nt"))
    //val triplesRDD2 = NTripleReader.load(spark, URI.create("/home/d/Desktop/data2.nt"))

    /*val intersectRDD= triplesRDD.intersection(triplesRDD2)    
    val graph = LoadGraph(intersectRDD)
    val init_paths = graph.mapVertices((id, attr) => (0, 0))
    var rec_paths = init_paths.mapVertices((id, attr) => (1,0))
    var paths: VertexRDD[(Int, Int)] = rec_paths.aggregateMessages[(Int, Int)]( predic => {predic.sendToDst(predic.dstAttr._1,predic.srcAttr._1)},(a, b) => (b._1,a._2 + b._2))
    var numpaths = paths.map(_._2._2).reduce(_ + _)
    for( i <- 2 to 10){
      rec_paths =  init_paths.joinVertices(paths)((id, old_attr, new_attr) => (new_attr._2, 0))
      paths = rec_paths.aggregateMessages[(Int, Int)]( predic => {if(predic.srcAttr._1>0) predic.sendToDst(predic.dstAttr._1,predic.srcAttr._1)},(a, b) => (b._1,a._2 + b._2))
      numpaths = paths.map(_._2._2).reduce(_ + _)
      println(numpaths)
    }*/
  }
}