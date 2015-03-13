import findata.datafetching.YFHistDataFetcher
import findata.toolset.FinTools
import java.util.concurrent.ConcurrentHashMap.ForEachKeyTask

object WorkSheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(234); 
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA");System.out.println("""testSymbols  : List[String] = """ + $show(testSymbols ));$skip(186); 

  testSymbols.foreach {
    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  };$skip(115); 

  val csvStr = scala.io.Source.fromURL(YFHistDataFetcher.getBaseUrl("AAPL")).getLines().foreach(s=> println(s));;System.out.println("""csvStr  : Unit = """ + $show(csvStr ))}

}
