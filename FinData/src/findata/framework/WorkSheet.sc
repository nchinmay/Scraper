import findata.datafetching.YFHistDataFetcher
import findata.datafetching.YFHistDataFetcherScala
import findata.toolset.FinTools

object WorkSheet {
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA")
                                                  //> testSymbols  : List[String] = List(AAL, DAL, MU, YHOO, AA, KORS, TSLA)

  //  testSymbols.foreach {
  //    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  //  }

  val csvStr = YFHistDataFetcherScala.getHistDataStream("AAPL", 20150301).foreach(s => println(s));
                                                  //> Date,Open,High,Low,Close,Volume,Adj Close
                                                  //| 2015-03-16,123.88,124.95,122.87,124.95,35507400,124.95
                                                  //| 2015-03-13,124.40,125.40,122.58,123.59,51827300,123.59
                                                  //| 2015-03-12,122.31,124.90,121.63,124.45,48362700,124.45
                                                  //| 2015-03-11,124.75,124.77,122.11,122.24,68939000,122.24
                                                  //| 2015-03-10,126.41,127.22,123.80,124.51,68856600,124.51
                                                  //| 2015-03-09,127.96,129.57,125.06,127.14,88528500,127.14
                                                  //| 2015-03-06,128.40,129.37,126.26,126.60,72842100,126.60
                                                  //| 2015-03-05,128.58,128.75,125.76,126.41,56517100,126.41
                                                  //| 2015-03-04,129.10,129.56,128.32,128.54,31666300,128.54
                                                  //| 2015-03-03,128.96,129.52,128.09,129.36,37816300,129.36
                                                  //| 2015-03-02,129.25,130.28,128.30,129.09,48096700,129.09
                                                  //| csvStr  : Unit = ()

}