package findata.datafetching

object WorkSheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val df = new YFFundamentalDataFetcherScala      //> df  : findata.datafetching.YFFundamentalDataFetcherScala = findata.datafetch
                                                  //| ing.YFFundamentalDataFetcherScala@76b0bfab
  // val xml = df.xml

  //  for (result <- xml.child) {
  //  df.parseQuery(result.child(0))
  //}

  val yffds = df.getYFFData(scala.collection.mutable.Set("AAPL", "MSFT"))
                                                  //> yffds  : scala.collection.mutable.Set[datalayer.objects.findata.YFFundamenta
                                                  //| lData] = Set(YData [Symbol=AAPL, Name=Apple Inc., StockExchange=NMS, YearLow
                                                  //| =94.84, YearHigh=134.54, ChangeFromYearLow=20.68, ChangeFromYearHigh=-19.02,
                                                  //|  PercentChangeFromYearLow=21.81, PercentChangeFromYearHigh=-14.14, FiftydayM
                                                  //| ovingAverage=124.29, ChangeFromFiftydayMovingAverage=-8.77, TwoHundreddayMov
                                                  //| ingAverage=125.49, ChangeFromTwoHundreddayMovingAverage=-9.97, AverageDailyV
                                                  //| olume=46889600, PERatio=13.36, PEGRatio=0.86, PriceSales=2.93, PriceBook=5.2
                                                  //| 3, EBITDA=7.788E10, MarketCapitalization=6.5878E11, EarningsShare=8.65, EPSE
                                                  //| stimateCurrentYear=9.13, EPSEstimateNextYear=9.78, EPSEstimateNextQuarter=3.
                                                  //| 19, DividendShare=2.08, DividendYield=1.7, ExDividendDate=8/6/2015, Dividend
                                                  //| PayDate=8/13/2015, PriceEPSEstimateCurrentYear=12.65, PriceEPSEstimateNextYe
                                                  //| ar=11.81, OneyrTargetPrice=147.0], YData [Symbol=MSFT, Name=Microsoft Corpor
                                                  //| ation, StockExchange=NMS
                                                  //| Output exceeds cutoff limit.

  for (yffd <- yffds)
    println(yffd.toString())                      //> YData [Symbol=AAPL, Name=Apple Inc., StockExchange=NMS, YearLow=94.84, YearH
                                                  //| igh=134.54, ChangeFromYearLow=20.68, ChangeFromYearHigh=-19.02, PercentChang
                                                  //| eFromYearLow=21.81, PercentChangeFromYearHigh=-14.14, FiftydayMovingAverage=
                                                  //| 124.29, ChangeFromFiftydayMovingAverage=-8.77, TwoHundreddayMovingAverage=12
                                                  //| 5.49, ChangeFromTwoHundreddayMovingAverage=-9.97, AverageDailyVolume=4688960
                                                  //| 0, PERatio=13.36, PEGRatio=0.86, PriceSales=2.93, PriceBook=5.23, EBITDA=7.7
                                                  //| 88E10, MarketCapitalization=6.5878E11, EarningsShare=8.65, EPSEstimateCurren
                                                  //| tYear=9.13, EPSEstimateNextYear=9.78, EPSEstimateNextQuarter=3.19, DividendS
                                                  //| hare=2.08, DividendYield=1.7, ExDividendDate=8/6/2015, DividendPayDate=8/13/
                                                  //| 2015, PriceEPSEstimateCurrentYear=12.65, PriceEPSEstimateNextYear=11.81, One
                                                  //| yrTargetPrice=147.0]
                                                  //| YData [Symbol=MSFT, Name=Microsoft Corporation, StockExchange=NMS, YearLow=4
                                                  //| 0.12, YearHigh=50.05, ChangeFromYearLow=6.62, ChangeFromYearHigh=-3.31, Perc
}