package findata.datafetching

object WorkSheet {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val df = new YFFundamentalDataFetcherScala      //> df  : findata.datafetching.YFFundamentalDataFetcherScala = findata.datafetch
                                                  //| ing.YFFundamentalDataFetcherScala@5ce81285
  val symbols = scala.io.Source.fromFile("C:\\Users\\Chinmay\\sws\\Scraper\\symbols.txt").getLines.toSet
                                                  //> symbols  : scala.collection.immutable.Set[String] = Set(MA, RF, TEG, CME, AM
                                                  //| ZN, MAT, APH, BCR, ADSK, HCBK, X, MSFT, ROST, YHOO, CPB, ABC, NSC, LYB, NEM,
                                                  //|  SWK, VLO, UTX, TEL, TYC, FISV, JEC, GWW, AGN, HSY, EIX, CELG, DNR, SYY, BRC
                                                  //| M, LUV, PLD, ESRX, AAPL, CRM, PFG, MUR, STX, CLX, FFIV, COG, NI, DNB, QEP, B
                                                  //| MS, SAI, GME, POM, GAS, COF, GNW, TJX, SYK, AMD, KIM, CAG, GPS, JBL, WLP, MO
                                                  //| S, APC, PCP, GD, BAC, PSA, JWN, CTSH, MKC, BXP, AES, STT, ALTR, TIE, MO, AXP
                                                  //| , AEE, IPG, PH, ANF, TIF, TWX, KMI, KSS, UNH, CCI, ABT, IVZ, DF, ETR, ALXN, 
                                                  //| WU, TRV, ETFC, CMCSA, OXY, DISCA, T, FCX, AKAM, BIIB, RL, AIZ, DLTR, ETN, RS
                                                  //| G, GILD, HES, XL, WAT, SJM, OMC, EXPE, TSN, LEN, NFLX, WEC, WPX, ALL, EA, AZ
                                                  //| O, PNR, DFS, BDX, PEG, CAH, XYL, AN, LNC, BIG, DELL, TE, FAST, LH, HAR, MAS,
                                                  //|  AIG, LMT, BAX, WFC, CF, F, PNW, CI, FLR, URBN, KMB, HUM, SCG, APD, A, LM, W
                                                  //| YN, AET, XOM, AVP, DG, MCHP, CERN, AMGN, IR, AFL, HPQ, MCD, JCP, BMC, DOW, R
                                                  //| HT, MPC, HD, LLL, HRS, Z
                                                  //| Output exceeds cutoff limit.
  val yffds = df.getYFFData(symbols)              //> yffds  : scala.collection.immutable.Map[String,datalayer.objects.findata.YFF
                                                  //| undamentalData] = Map(MA -> MA, Name=Mastercard Incorporated Common, StockEx
                                                  //| change=NYQ, YearLow=69.64, YearHigh=99.18, ChangeFromYearLow=27.4, ChangeFro
                                                  //| mYearHigh=-2.14, PercentChangeFromYearLow=39.35, PercentChangeFromYearHigh=-
                                                  //| 2.16, FiftydayMovingAverage=95.71, ChangeFromFiftydayMovingAverage=1.33, Two
                                                  //| HundreddayMovingAverage=91.31, ChangeFromTwoHundreddayMovingAverage=5.73, Av
                                                  //| erageDailyVolume=3677060, PERatio=29.77, PEGRatio=1.64, PriceSales=11.52, Pr
                                                  //| iceBook=17.21, EBITDA=5.45E9, MarketCapitalization=1.0986E11, EarningsShare=
                                                  //| 3.26, EPSEstimateCurrentYear=3.38, EPSEstimateNextYear=4.06, EPSEstimateNext
                                                  //| Quarter=0.77, DividendShare=0.64, DividendYield=0.7, ExDividendDate=7/7/2015
                                                  //| , DividendPayDate=8/10/2015, PriceEPSEstimateCurrentYear=28.71, PriceEPSEsti
                                                  //| mateNextYear=23.9, OneyrTargetPrice=106.75], RF -> RF, Name=Regions Financia
                                                  //| l Corporation C, StockEx
                                                  //| Output exceeds cutoff limit.

  val recdSet = for (symbol <- yffds.keySet) yield symbol
                                                  //> recdSet  : scala.collection.immutable.Set[String] = Set(MA, RF, TEG, CME, AM
                                                  //| ZN, MAT, APH, BCR, ADSK, HCBK, X, MSFT, ROST, YHOO, CPB, ABC, NSC, LYB, NEM,
                                                  //|  SWK, VLO, UTX, TEL, TYC, FISV, JEC, GWW, AGN, HSY, EIX, CELG, DNR, SYY, BRC
                                                  //| M, LUV, PLD, ESRX, AAPL, CRM, PFG, MUR, STX, CLX, FFIV, COG, NI, DNB, QEP, B
                                                  //| MS, SAI, GME, POM, GAS, COF, GNW, TJX, SYK, AMD, KIM, CAG, GPS, JBL, WLP, MO
                                                  //| S, APC, PCP, GD, BAC, PSA, JWN, CTSH, MKC, BXP, AES, STT, ALTR, TIE, MO, AXP
                                                  //| , AEE, IPG, PH, ANF, TIF, TWX, KMI, KSS, UNH, CCI, ABT, IVZ, DF, ETR, ALXN, 
                                                  //| WU, TRV, ETFC, CMCSA, OXY, DISCA, T, FCX, AKAM, BIIB, RL, AIZ, DLTR, ETN, RS
                                                  //| G, GILD, HES, XL, WAT, SJM, OMC, EXPE, TSN, LEN, NFLX, WEC, WPX, ALL, EA, AZ
                                                  //| O, PNR, DFS, BDX, PEG, CAH, XYL, AN, LNC, BIG, DELL, TE, FAST, LH, HAR, MAS,
                                                  //|  AIG, LMT, BAX, WFC, CF, F, PNW, CI, FLR, URBN, KMB, HUM, SCG, APD, A, LM, W
                                                  //| YN, AET, XOM, AVP, DG, MCHP, CERN, AMGN, IR, AFL, HPQ, MCD, JCP, BMC, DOW, R
                                                  //| HT, MPC, HD, LLL, HRS, Z
                                                  //| Output exceeds cutoff limit.

  symbols -- recdSet.intersect(symbols)           //> res0: scala.collection.immutable.Set[String] = Set()
  
  

}