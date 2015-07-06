package findata.datafetching

object WorkSheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  Set("a", "b", "c").mkString("\"", "\",\"", "\"")//> res0: String = "a","b","c"
  
  val df = new YFFundamentalDataFetcherScala      //> <?xml version="1.0" encoding="UTF-8"?>
                                                  //| <query xmlns:yahoo="http://www.yahooapis.com/v1/base.rng" yahoo:count="1" ya
                                                  //| hoo:created="2015-07-05T23:15:10Z" yahoo:lang="en-US"><results><quote symbol
                                                  //| ="AAPL"><Ask>126.44</Ask><AverageDailyVolume>44657400</AverageDailyVolume><B
                                                  //| id>126.31</Bid><AskRealtime/><BidRealtime/><BookValue>22.39</BookValue><Chan
                                                  //| ge_PercentChange>-0.16 - -0.13%</Change_PercentChange><Change>-0.16</Change>
                                                  //| <Commission/><Currency>USD</Currency><ChangeRealtime/><AfterHoursChangeRealt
                                                  //| ime/><DividendShare>2.08</DividendShare><LastTradeDate>7/2/2015</LastTradeDa
                                                  //| te><TradeDate/><EarningsShare>8.05</EarningsShare><ErrorIndicationreturnedfo
                                                  //| rsymbolchangedinvalid/><EPSEstimateCurrentYear>9.04</EPSEstimateCurrentYear>
                                                  //| <EPSEstimateNextYear>9.72</EPSEstimateNextYear><EPSEstimateNextQuarter>1.84<
                                                  //| /EPSEstimateNextQuarter><DaysLow>125.77</DaysLow><DaysHigh>126.69</DaysHigh>
                                                  //| <YearLow>92.57</YearLow><YearHigh>134.54</YearHigh><HoldingsG
                                                  //| Output exceeds cutoff limit.
  
}