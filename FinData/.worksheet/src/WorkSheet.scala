import findata.datafetching.YFHistDataFetcher
import findata.datafetching.YFHistDataFetcherScala
import findata.toolset.FinTools

object WorkSheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(224); 
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA");System.out.println("""testSymbols  : List[String] = """ + $show(testSymbols ));$skip(300); 

  //  testSymbols.foreach {
  //    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  //  }

  val csvStr = YFHistDataFetcherScala.getHistDataStream("AAPL", 20150301).foreach(s => println(s));;System.out.println("""csvStr  : Unit = """ + $show(csvStr ))}

}
