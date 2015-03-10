import findata.datafetching.YFHistDataFetcher
import findata.toolset.FinTools

object WorkSheet {
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA")
                                                  //> testSymbols  : List[String] = List(AAL, DAL, MU, YHOO, AA, KORS, TSLA)

  testSymbols.foreach {
    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  }                                               //> 0.7393543469206623
                                                  //| 0.8889562057980568
                                                  //| 0.5636124537980262
                                                  //| 0.5535344755379464
                                                  //| 0.6248775673442035
                                                  //| -1.2921632766224973
                                                  //| -0.32588802718804505

}