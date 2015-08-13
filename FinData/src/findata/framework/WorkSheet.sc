import findata.datafetching.YFHistDataFetcher
import findata.datafetching.YFHistDataFetcherScala
import findata.toolset.FinTools
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkConf

object WorkSheet {
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA")
                                                  //> testSymbols  : List[String] = List(AAL, DAL, MU, YHOO, AA, KORS, TSLA)

  //  testSymbols.foreach {
  //    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  //  }

  val csvStr = YFHistDataFetcherScala.getHistDataCSVStream("AAPL", 20150301).get.foreach(l => { l.foreach(s => print(s + "\t")); println })
                                                  //> 2015-08-12	112.529999	115.419998	109.629997	115.239998	
                                                  //| 101217500	115.239998	
                                                  //| 2015-08-11	117.809998	118.18	113.330002	113.489998	95711900
                                                  //| 	113.489998	
                                                  //| 2015-08-10	116.529999	119.989998	116.529999	119.720001	
                                                  //| 54538500	119.720001	
                                                  //| 2015-08-07	114.580002	116.25	114.50	115.519997	38421400	
                                                  //| 115.519997	
                                                  //| 2015-08-06	115.970001	116.50	114.120003	115.129997	52903000
                                                  //| 	115.129997	
                                                  //| 2015-08-05	112.949997	117.440002	112.099998	115.400002	
                                                  //| 99312600	114.880003	
                                                  //| 2015-08-04	117.419998	117.699997	113.25	114.639999	12413860
                                                  //| 0	114.123426	
                                                  //| 2015-08-03	121.50	122.57	117.519997	118.440002	69976000	
                                                  //| 117.906306	
                                                  //| 2015-07-31	122.599998	122.639999	120.910004	121.300003	
                                                  //| 42885000	120.753419	
                                                  //| 2015-07-30	122.32	122.57	121.709999	122.370003	33628300	
                                                  //| 121.818597	
                                                  //| 2015-07-29	123.150002	123.50	122.269997	122.989998	37011700
                                                  //| 	122.435799	
                                                  //| 2015-07-28	123.379997	123.910004	122.550003	123.379997	
                                                  //| 33618100	122.824041	
                                                  //| 20
                                                  //| Output exceeds cutoff limit.

  // val scConf = new SparkConf().setMaster("spark://master:7077")
  // val sc = SparkContext.getOrCreate(scConf)

}