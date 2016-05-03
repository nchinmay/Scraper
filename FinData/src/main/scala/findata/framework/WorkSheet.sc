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
  val df = CsvParser.parse(csvSource).withRowIndex(0).withColIndex(0)
                                                  //> df  : org.saddle.Frame[String,String,String] = [296 x 6]
                                                  //|                     Open       High        Low      Close    Volume  Adj Clo
                                                  //| se 
                                                  //|               ---------- ---------- ---------- ---------- --------- --------
                                                  //| -- 
                                                  //| 2016-05-02 ->  93.970001  94.080002  92.400002  93.639999  47736900  93.6399
                                                  //| 99 
                                                  //| 2016-04-29 ->  93.989998  94.720001  92.510002  93.739998  68334400  93.7399
                                                  //| 98 
                                                  //| 2016-04-28 ->  97.610001  97.879997      94.25  94.830002  81990700  94.8300
                                                  //| 02 
                                                  //| 2016-04-27 ->      96.00  98.709999      95.68      97.82 113538400      97.
                                                  //| 82 
                                                  //| 2016-04-26 -> 103.910004 105.300003 103.910004 104.349998  40287500 104.3499
                                                  //| 98 
                                                  //| ...
                                                  //| 2015-03-06 -> 128.399994 129.369995 126.260002 126.599998  72842100 124.2958
                                                  //| 89 
                                                  //| 2015-03-05 -> 128.580002     128.75 125.760002 126.410004  56517100 124.1093
                                                  //| 52 
                                                  //| 2015-03-04 -> 129.100006 129.559998 128.320007 128.539993  31666300 126.2005
                                                  //| 76 
                                                  //| 2015-03-03 -> 128.960007 129.520004 1
                                                  //| Output exceeds cutoff limit.
}