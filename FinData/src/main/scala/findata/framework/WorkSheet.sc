import findata.datafetching.YFHistDataFetcher
import findata.datafetching.YFHistDataFetcherScala
import findata.toolset.FinTools

object WorkSheet {
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA")
                                                  //> testSymbols  : List[String] = List(AAL, DAL, MU, YHOO, AA, KORS, TSLA)

  //  testSymbols.foreach {
  //    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  //  }

  val csvStr = YFHistDataFetcherScala.getHistDataCSVStream("AAPL", 20150301).get.foreach(l => { l.foreach(s => print(s + "\t")); println })
                                                  //> 2016-04-28	97.610001	97.879997	94.25	94.830002	81990700
                                                  //| 	94.830002	
                                                  //| 2016-04-27	96.00	98.709999	95.68	97.82	113538400	97.82	
                                                  //| 
                                                  //| 2016-04-26	103.910004	105.300003	103.910004	104.349998	
                                                  //| 40287500	104.349998	
                                                  //| 2016-04-25	105.00	105.650002	104.510002	105.080002	27951000
                                                  //| 	105.080002	
                                                  //| 2016-04-22	105.010002	106.480003	104.620003	105.68	33477100
                                                  //| 	105.68	
                                                  //| 2016-04-21	106.93	106.93	105.519997	105.970001	31356400	
                                                  //| 105.970001	
                                                  //| 2016-04-20	106.639999	108.089996	106.059998	107.129997	
                                                  //| 28666900	107.129997	
                                                  //| 2016-04-19	107.879997	108.00	106.230003	106.910004	32292300
                                                  //| 	106.910004	
                                                  //| 2016-04-18	108.889999	108.949997	106.940002	107.480003	
                                                  //| 60834000	107.480003	
                                                  //| 2016-04-15	112.110001	112.300003	109.730003	109.849998	
                                                  //| 46418500	109.849998	
                                                  //| 2016-04-14	111.620003	112.389999	111.330002	112.099998	
                                                  //| 25337400	112.099998	
                                                  //| 2016-04-13	110.800003	112.339996	110.800003	112.040001	
                                                  //| 32691800	112.040001	
                                                  //| 2016-04-12	109.339996	110.50	
                                                  //| Output exceeds cutoff limit.

  // val scConf = new SparkConf().setMaster("spark://master:7077")
  // val sc = SparkContext.getOrCreate(scConf)

}