import findata.datafetching.YFHistDataFetcher
import findata.datafetching.YFHistDataFetcherScala
import findata.toolset.FinTools

object WorkSheet {
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA")
                                                  //> testSymbols  : List[String] = List(AAL, DAL, MU, YHOO, AA, KORS, TSLA)

  //  testSymbols.foreach {
  //    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  //  }

  val csvStr = scala.io.Source.fromURL(YFHistDataFetcherScala.getBaseUrl("AAPL")).getLines().foreach(s => println(s));
                                                  //> Date,Open,High,Low,Close,Volume,Adj Close
                                                  //| 2015-03-13,124.40,125.40,122.58,123.59,51675300,123.59
                                                  //| 2015-03-12,122.31,124.90,121.63,124.45,48145700,124.45
                                                  //| 2015-03-11,124.75,124.77,122.11,122.24,68582700,122.24
                                                  //| 2015-03-10,126.41,127.22,123.80,124.51,68240700,124.51
                                                  //| 2015-03-09,127.96,129.57,125.06,127.14,88347500,127.14
                                                  //| 2015-03-06,128.40,129.37,126.26,126.60,72225800,126.60
                                                  //| 2015-03-05,128.58,128.75,125.76,126.41,56004600,126.41
                                                  //| 2015-03-04,129.10,129.56,128.32,128.54,30977700,128.54
                                                  //| 2015-03-03,128.96,129.52,128.09,129.36,37816300,129.36
                                                  //| 2015-03-02,129.25,130.28,128.30,129.09,48096700,129.09
                                                  //| 2015-02-27,130.00,130.57,128.24,128.46,62014800,128.46
                                                  //| 2015-02-26,128.79,130.87,126.61,130.42,91287500,130.42
                                                  //| 2015-02-25,131.56,131.60,128.15,128.79,74711700,128.79
                                                  //| 2015-02-24,132.94,133.60,131.17,132.17,69228100,132.17
                                                  //| 2015-02-23,130.02,133.00,129.66,133.00,70974100,133.00
                                                  //| 2015-02-20,128.62,129.50,128.05,129.50,48948400,1
                                                  //| Output exceeds cutoff limit.-

}