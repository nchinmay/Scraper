package findata.datafetching

import java.util.Date
import sun.security.util.Length

object YFHistDataFetcherScala {
  val YH_DATA_START_DATE: Int = 19620001;
  val YF_ICHART_URL_PREFIX = "http://ichart.finance.yahoo.com/table.csv?s="
  val YF_ICHART_URL_SUFFIX = "&c=1962"

  def getBaseUrl(ticker: String): String = {
    //YF_ICHART_URL_PREFIX + ticker + YF_ICHART_URL_SUFFIX
    getBaseUrl(ticker, YH_DATA_START_DATE)
  }

  def getBaseUrl(ticker: String, from: Int): String = {
    val fromStr = from.toString
    if (fromStr.length == 8) {
      val fromYear = fromStr.substring(0, 4)
      val fromMonth = fromStr.substring(4, 6).toInt - 1
      val fromDay = fromStr.substring(6, 8)
      YF_ICHART_URL_PREFIX + ticker + "&a=" + fromMonth + "&b=" + fromDay + "&c=" + fromYear
    } else null
  }
}