import findata.datafetching.YFHistDataFetcher
import findata.toolset.FinTools
import dispatch._, Defaults._

object WorkSheet {
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA")
  //> testSymbols  : List[String] = List(AAL, DAL, MU, YHOO, AA, KORS, TSLA)

  testSymbols.foreach {
    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  } //> 0.674062294910738
  //| 0.8091996252699455
  //| 0.46492315052634237
  //| 0.5293379421223183
  //| 0.5756498895259323
  //| -1.2696766496291516
  //| -0.332243141789663

  val baseurl = url(YFHistDataFetcher.getBaseUrl("AAPL"))
  //> svc  : dispatch.Req = Req(<function1>,Properties(NoBody))
  val csvStr = Http(baseurl OK as.String)

}