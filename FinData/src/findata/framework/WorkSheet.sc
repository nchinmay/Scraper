import findata.datafetching.YFHistDataFetcher
import findata.datafetching.YFHistDataFetcherScala
import findata.toolset.FinTools
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.SparkConf

object WorkSheet {
  val testSymbols = List("AAL", "DAL", "MU", "YHOO", "AA", "KORS", "TSLA")
                                                  //> testSymbols  : List[String] = List(AAL, DAL, MU, YHOO, AA, KORS, TSLA)

  //  testSymbols.foreach {
  //    s => val histStream = YFHistDataFetcher.getSortedHistDataStream(s, 20140309); println(FinTools.getSharpeRatio(FinTools.getIntervalReturns(histStream)))
  //  }

  val csvStr = YFHistDataFetcherScala.getHistDataCSVStream("AAPL", 20150301).foreach(l => { l.foreach(s => print(s + "\t")); println })
                                                  //> 2015-06-24	127.209999	129.800003	127.120003	128.110001	
                                                  //| 54964900	128.110001	
                                                  //| 2015-06-23	127.480003	127.610001	126.879997	127.029999	
                                                  //| 30137100	127.029999	
                                                  //| 2015-06-22	127.489998	128.059998	127.080002	127.610001	
                                                  //| 33833500	127.610001	
                                                  //| 2015-06-19	127.709999	127.82	126.400002	126.599998	54181300
                                                  //| 	126.599998	
                                                  //| 2015-06-18	127.230003	128.309998	127.220001	127.879997	
                                                  //| 35241100	127.879997	
                                                  //| 2015-06-17	127.720001	127.879997	126.739998	127.300003	
                                                  //| 32768500	127.300003	
                                                  //| 2015-06-16	127.029999	127.849998	126.370003	127.599998	
                                                  //| 31404000	127.599998	
                                                  //| 2015-06-15	126.099998	127.239998	125.709999	126.919998	
                                                  //| 39842600	126.919998	
                                                  //| 2015-06-12	128.190002	128.330002	127.110001	127.169998	
                                                  //| 36754200	127.169998	
                                                  //| 2015-06-11	129.179993	130.179993	128.479996	128.589996	
                                                  //| 35260400	128.589996	
                                                  //| 2015-06-10	127.919998	129.339996	127.849998	128.880005	
                                                  //| 38915900	128.880005	
                                                  //| 2015-06-09	126.699997	128.080002	125.620003	127.419998
                                                  //| Output exceeds cutoff limit.

  val scConf = new SparkConf().setMaster("spark://master:7077")
                                                  //> scConf  : org.apache.spark.SparkConf = org.apache.spark.SparkConf@18a70f16
  val sc = SparkContext.getOrCreate(scConf)       //> Using Spark's default log4j profile: org/apache/spark/log4j-defaults.propert
                                                  //| ies
                                                  //| 15/06/25 00:52:46 INFO SparkContext: Running Spark version 1.4.0
                                                  //| 15/06/25 00:52:51 WARN NativeCodeLoader: Unable to load native-hadoop librar
                                                  //| y for your platform... using builtin-java classes where applicable
                                                  //| 15/06/25 00:52:51 ERROR SparkContext: Error initializing SparkContext.
                                                  //| org.apache.spark.SparkException: An application name must be set in your con
                                                  //| figuration
                                                  //| 	at org.apache.spark.SparkContext.<init>(SparkContext.scala:371)
                                                  //| 	at org.apache.spark.SparkContext$.getOrCreate(SparkContext.scala:2107)
                                                  //| 	at WorkSheet$$anonfun$main$1.apply$mcV$sp(WorkSheet.scala:18)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupp
                                                  //| Output exceeds cutoff limit.

}