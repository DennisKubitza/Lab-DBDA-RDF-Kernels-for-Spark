[![Codacy Badge](https://api.codacy.com/project/badge/Grade/da88f5d82fdb4c08a9640d023af36442)](https://www.codacy.com/app/DennisKubitza/Lab-DBDA-RDF-Kernels-for-Spark?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=DennisKubitza/Lab-DBDA-RDF-Kernels-for-Spark&amp;utm_campaign=Badge_Grade)
[![AUR](https://img.shields.io/aur/license/yaourt.svg)]()
# Lab-DBDA-RDF-Kernels-for-Spark

##Description
Implementation of methods for computing kernel functions for intersection graphs and intersection trees. Designed to process RDF data packed in Spark Resilient Distributed Datasets. Based on and requires [SANSA-Stack](https://github.com/SANSA-Stack/) for operating.

##Available methods and their usage

```scala
/**
 	* Calculates adjacency matrix for intersection of two RDF graphs.
 	* @param firstRDD Resilient Distributed Dataset containing entries of RDF triples.
 	* @param secondRDD Resilient Distributed Dataset containing entries of RDF triples.
 	* @return CoordinateMatrix containing '1' at the positions where edges exist and '0'
 	* otherwise.
 	*/
  def intersectionAdjacencyMatrix(firstRDD: RDD[Triple], secondRDD: RDD[Triple]) : CoordinateMatrix
```

