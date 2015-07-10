package findata.datafetching

object WorkSheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val df = new YFFundamentalDataFetcherScala      //> df  : findata.datafetching.YFFundamentalDataFetcherScala = findata.datafetch
                                                  //| ing.YFFundamentalDataFetcherScala@76b0bfab
  val xml = df.xml                                //> xml  : scala.xml.Elem = <query yahoo:lang="en-US" yahoo:created="2015-07-10T
                                                  //| 03:25:29Z" yahoo:count="1" xmlns:yahoo="http://www.yahooapis.com/v1/base.rng
                                                  //| "><results><quote symbol="AAPL"><Ask>121.05</Ask><AverageDailyVolume>4579480
                                                  //| 0</AverageDailyVolume><Bid>120.98</Bid><AskRealtime/><BidRealtime/><BookValu
                                                  //| e>22.39</BookValue><Change_PercentChange>-2.50 - -2.04%</Change_PercentChang
                                                  //| e><Change>-2.50</Change><Commission/><Currency>USD</Currency><ChangeRealtime
                                                  //| /><AfterHoursChangeRealtime/><DividendShare>2.08</DividendShare><LastTradeDa
                                                  //| te>7/9/2015</LastTradeDate><TradeDate/><EarningsShare>8.05</EarningsShare><E
                                                  //| rrorIndicationreturnedforsymbolchangedinvalid/><EPSEstimateCurrentYear>9.04<
                                                  //| /EPSEstimateCurrentYear><EPSEstimateNextYear>9.73</EPSEstimateNextYear><EPSE
                                                  //| stimateNextQuarter>1.86</EPSEstimateNextQuarter><DaysLow>119.22</DaysLow><Da
                                                  //| ysHigh>124.06</DaysHigh><YearLow>92.57</YearLow><YearHigh>134.54</YearHigh><
                                                  //| HoldingsGainPercent/><An
                                                  //| Output exceeds cutoff limit.

  for (result <- xml.child) {
    df.parseQuery(result.child(0))                //> java.lang.NumberFormatException: For input string: "691.73B"
                                                  //| 	at sun.misc.FloatingDecimal.readJavaFormatString(FloatingDecimal.java:20
                                                  //| 43)
                                                  //| 	at sun.misc.FloatingDecimal.parseDouble(FloatingDecimal.java:110)
                                                  //| 	at java.lang.Double.parseDouble(Double.java:538)
                                                  //| 	at scala.collection.immutable.StringLike$class.toDouble(StringLike.scala
                                                  //| :284)
                                                  //| 	at scala.collection.immutable.StringOps.toDouble(StringOps.scala:30)
                                                  //| 	at findata.datafetching.YFFundamentalDataFetcherScala$$anonfun$parseQuer
                                                  //| y$1.apply(YFFundamentalDataFetcherScala.scala:57)
                                                  //| 	at findata.datafetching.YFFundamentalDataFetcherScala$$anonfun$parseQuer
                                                  //| y$1.apply(YFFundamentalDataFetcherScala.scala:34)
                                                  //| 	at scala.collection.immutable.List.foreach(List.scala:381)
                                                  //| 	at findata.datafetching.YFFundamentalDataFetcherScala.parseQuery(YFFunda
                                                  //| mentalDataFetcherScala.scala:34)
                                                  //| 	at findata.datafetching.WorkSheet$$anonfun$main$1$$anonfun$apply$mcV$sp$
                                                  //| 1.apply(findata.datafetchin
                                                  //| Output exceeds cutoff limit.
  }

}