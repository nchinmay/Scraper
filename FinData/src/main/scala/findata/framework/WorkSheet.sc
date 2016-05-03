import findata.datafetching.YFHistDataFetcher
import findata.datafetching.YFHistDataFetcherScala
import findata.toolset.FinTools
import findata.framework._
import org.saddle.io._

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

  val csvSource = new CsvUrl(url)                 //> csvSource  : findata.framework.CsvUrl = CsvUrl(http://ichart.finance.yahoo.c
                                                  //| om/table.csv?s=AAPL&a=2&b=1&c=2015, encoding: UTF-8)
  val df = CsvParser.parse(csvSource).withRowIndex(0).withColIndex(0).col("Adj Close")
                                                  //> df  : org.saddle.Frame[String,String,String] = [296 x 1]
                                                  //|                Adj Close 
                                                  //|               ---------- 
                                                  //| 2016-05-02 ->  93.639999 
                                                  //| 2016-04-29 ->  93.739998 
                                                  //| 2016-04-28 ->  94.830002 
                                                  //| 2016-04-27 ->      97.82 
                                                  //| 2016-04-26 -> 104.349998 
                                                  //| ...
                                                  //| 2015-03-06 -> 124.295889 
                                                  //| 2015-03-05 -> 124.109352 
                                                  //| 2015-03-04 -> 126.200576 
                                                  //| 2015-03-03 -> 127.005659 
                                                  //| 2015-03-02 -> 126.740569 
                                                  //| 
}