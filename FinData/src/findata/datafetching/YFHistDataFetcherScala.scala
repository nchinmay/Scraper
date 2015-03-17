package findata.datafetching

import java.util.Date
import sun.security.util.Length
import scala.io.BufferedSource
import scala.collection.Iterator

object YFHistDataFetcherScala {
  val YH_DATA_START_DATE: Int = 19620101;
  val YF_ICHART_URL_PREFIX = "http://ichart.finance.yahoo.com/table.csv?s="
  val YF_ICHART_URL_SUFFIX = "&c=1962"

  def getBaseUrl(ticker: String): String = {
    getBaseUrl(ticker, YH_DATA_START_DATE)
  }

  def getBaseUrl(ticker: String, from: Int): String = {
    val fromStr = from.toString
    if (fromStr.length == 8) {
      val fromYear = fromStr.substring(0, 4)
      val fromMonth = fromStr.substring(4, 6).toInt - 1
      val fromDay = fromStr.substring(6, 8).toInt
      YF_ICHART_URL_PREFIX + ticker + "&a=" + fromMonth + "&b=" + fromDay + "&c=" + fromYear
    } else null
  }

  def getHistDataCSVStream(ticker: String, from: Int): Iterator[Array[String]] = {
    scala.io.Source.fromURL(YFHistDataFetcherScala.getBaseUrl(ticker, from)).getLines().drop(1).map(_.split(","))
  }
}