package findata.datafetching

object WorkSheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val df = new YFFundamentalDataFetcherScala      //> df  : findata.datafetching.YFFundamentalDataFetcherScala = findata.datafetch
                                                  //| ing.YFFundamentalDataFetcherScala@3712b94
  val xml = df.xml                                //> xml  : scala.xml.Elem = <query yahoo:lang="en-US" yahoo:created="2015-07-08T
                                                  //| 03:04:22Z" yahoo:count="1" xmlns:yahoo="http://www.yahooapis.com/v1/base.rng
                                                  //| "><results><quote symbol="AAPL"><Ask>125.49</Ask><AverageDailyVolume>4469960
                                                  //| 0</AverageDailyVolume><Bid>125.36</Bid><AskRealtime/><BidRealtime/><BookValu
                                                  //| e>22.39</BookValue><Change_PercentChange>-0.31 - -0.25%</Change_PercentChang
                                                  //| e><Change>-0.31</Change><Commission/><Currency>USD</Currency><ChangeRealtime
                                                  //| /><AfterHoursChangeRealtime/><DividendShare>2.08</DividendShare><LastTradeDa
                                                  //| te>7/7/2015</LastTradeDate><TradeDate/><EarningsShare>8.05</EarningsShare><E
                                                  //| rrorIndicationreturnedforsymbolchangedinvalid/><EPSEstimateCurrentYear>9.04<
                                                  //| /EPSEstimateCurrentYear><EPSEstimateNextYear>9.73</EPSEstimateNextYear><EPSE
                                                  //| stimateNextQuarter>1.85</EPSEstimateNextQuarter><DaysLow>123.77</DaysLow><Da
                                                  //| ysHigh>126.15</DaysHigh><YearLow>92.57</YearLow><YearHigh>134.54</YearHigh><
                                                  //| HoldingsGainPercent/><An
                                                  //| Output exceeds cutoff limit.

  for (result <- xml.child) {
    df.parseQuery(result.child(0))
  }

}