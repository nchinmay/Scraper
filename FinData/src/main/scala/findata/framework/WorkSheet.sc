import findata.datafetching.YFHistDataFetcher
import findata.datafetching.YFHistDataFetcherScala
import findata.toolset.FinTools
import joinery.DataFrame

object WorkSheet {
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA")
                                                  //> testSymbols  : List[String] = List(AAL, DAL, MU, YHOO, AA, KORS, TSLA)

  //  testSymbols.foreach {
  //    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  //  }

  // val csvStr = YFHistDataFetcherScala.getHistDataCSVStream("AAPL", 20150301).get.foreach(l => { l.foreach(s => print(s + "\t")); println })

  val url = YFHistDataFetcherScala.getBaseUrl("AAPL", 20150301)
                                                  //> url  : String = http://ichart.finance.yahoo.com/table.csv?s=AAPL&a=2&b=1&c=2
                                                  //| 015

  val dfAAPL = DataFrame.readCsv(YFHistDataFetcherScala.getBaseUrl("AAPL", 20150301), ",").retain("Date", "Adj Close")
                                                  //> dfAAPL  : joinery.DataFrame[Object] =     	      Date	    Adj Close
                                                  //|    0	2016-04-29	  93.73999800
                                                  //|    1	2016-04-28	  94.83000200
                                                  //|    2	2016-04-27	  97.82000000
                                                  //|    3	2016-04-26	 104.34999800
                                                  //|    4	2016-04-25	 105.08000200
                                                  //|    5	2016-04-22	 105.68000000
                                                  //|    6	2016-04-21	 105.97000100
                                                  //|    7	2016-04-20	 107.12999700
                                                  //|    8	2016-04-19	 106.91000400
                                                  //| 
                                                  //| ... 285 rows skipped ...
                                                  //| 
                                                  //|  294	2015-03-02	 126.74056900
                                                  //| 
  //val dfGOOG = DataFrame.readCsv(YFHistDataFetcherScala.getBaseUrl("GOOG", 20150301), ",").retain("Date", "Adj Close")
//val df = dfAAPL.joinOn(dfGOOG, "Date")
}