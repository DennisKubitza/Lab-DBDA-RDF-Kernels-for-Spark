package rdfKernels
	
import org.apache.spark._
import scala.util.Random
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
import org.apache.spark.sql._
import scala.Stream

class kernelFunctions {
  
  def test(){
    
    // Calculation of Pahts of lenth l:
    // Input: d, triplesRDD, triplesRDD 2, lambda
    /***
     * 
     * val intersectRDD= triplesRDD.intersection(triplesRDD2)   
     
     val lambda = 0.9
     var kernel = 0   
    val d = 9
    val graph = LoadGraph(triplesRDD2)
    var rec_paths = graph.mapVertices((id,attr) => (List(List(id)),List(List())))
    var walk_create: VertexRDD[(List[List[VertexId]], List[List[VertexId]])] = 
      rec_paths.aggregateMessages[(List[List[VertexId]],List[List[VertexId]])]( predic => 
       {predic.sendToDst(predic.dstAttr._1,predic.srcAttr._1)},
      (a, b) => (b._1,a._2 ++ b._2))
    var paths = walk_create.mapValues((id,attr) => 
      (attr._2.filter(!_.contains(id)).map(_ ++ List(id)) , List(List())))
    val numpaths = (paths.map(_._2._1.length).reduce(_ + _))
    kernel += lambda * numpaths
    for( i <- 2 to d){
     rec_paths = rec_paths.joinVertices(paths)((id,old_attr, new_attr) => (new_attr._1, new_attr._2))
     walk_create = rec_paths.aggregateMessages[(List[List[VertexId]],List[List[VertexId]])]( predic => 
       {predic.sendToDst(predic.dstAttr._1,predic.srcAttr._1)},
      (a, b) => (b._1,a._2 ++ b._2))
     paths = walk_create.mapValues((id,attr) => 
      (attr._2.filter(!_.contains(id)).map(_ ++ List(id)) , List(List())))
     val numpaths = (paths.map(_._2._1.length).reduce(_ + _))
     kernel += scala.math.pow(lambda,d)*numpaths
    }
    
  }***/
    
    // Calculation of Pahts of lenth l:
    // Input: d, triplesRDD, triplesRDD 2, lambda
    
    //val triplesRDD = NTripleReader.load(spark, URI.create("/home/d/Desktop/infobox_en/data.nt"))
    //val triplesRDD2 = NTripleReader.load(spark, URI.create("/home/d/Desktop/data2.nt"))
    
    /*val intersectRDD= triplesRDD.intersection(triplesRDD2)   
      val d = 8
      val lambda = 0.9
    var kernel = 0
    val graph = LoadGraph(intersectRDD)
    val init_paths = graph.mapVertices((id, attr) => (0, 0))
    var rec_paths = init_paths.mapVertices((id, attr) => (1,0))
    var paths: VertexRDD[(Int, Int)] = rec_paths.aggregateMessages[(Int, Int)]( predic => {predic.sendToDst(predic.dstAttr._1,predic.srcAttr._1)},(a, b) => (b._1,a._2 + b._2))
    var numpaths = paths.map(_._2._2).reduce(_ + _)
    kernel += lambda *numpaths 
    for( i <- 2 to d){
      rec_paths =  init_paths.joinVertices(paths)((id, old_attr, new_attr) => (new_attr._2, 0))
      paths = rec_paths.aggregateMessages[(Int, Int)]( predic => {if(predic.srcAttr._1>0) predic.sendToDst(predic.dstAttr._1,predic.srcAttr._1)},(a, b) => (b._1,a._2 + b._2))
      numpaths = paths.map(_._2._2).reduce(_ + _)
     	kernel += scala.math.pow(lambda,d)*numpaths
    }*/
    
    /***Generate all Elements of the intersction Tree Not necessary anymore
     * 
    var init_paths = graph.mapVertices((id, attr) =>  
      if(id==0)(d+1, 0) else if (id==5)(0, d+1) else(0,0))
    var sectTree = init_paths.aggregateMessages[(Int, Int )]( 
          predic => {if(false)predic.sendToDst(0,0)},
          (a, b) => (0,0)) 
    
    for( i <- 1 to d){
      sectTree = init_paths.aggregateMessages[(Int, Int)]( 
          predic => {if(predic.srcAttr._1>1 || predic.srcAttr._2>1)predic.sendToDst(predic.srcAttr._1-1,predic.srcAttr._2-1)},
          (a, b) => (math.max(math.max(a._1,b._1),0),math.max(math.max(a._2,b._2),0)))  
      init_paths =  init_paths.joinVertices(sectTree)((id, old_attr, new_attr) => (math.max(new_attr._1,old_attr._1), math.max(old_attr._2,new_attr._2)))
      var subset = init_paths.mapVertices((id,attr) => if(attr._1 > 0 && attr._2 > 0) 1 else 0)
      println(subset.vertices.map(_._2).sum())
    }
    
    sectTree.take(10).foreach(println(_))
    */
  }
}
