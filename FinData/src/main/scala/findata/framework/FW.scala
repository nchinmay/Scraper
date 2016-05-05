package findata.framework

import findata.datafetching.YFHistDataFetcherScala
import org.saddle.io.CsvParser
import org.joda.time.DateTime
import java.util.Formatter.DateTime
import java.text.SimpleDateFormat
import org.saddle.Frame
import java.util.Date
import org.apache.commons.math3.util.FastMath
import findata.datafetching.YFFundamentalDataFetcherScala
import org.saddle.Series

object FW {
  val ADJ_CLOSE = "Adj Close"
  val YHIST_DATA_SDF = new SimpleDateFormat(YFHistDataFetcherScala.YH_DATA_DATE_FORMAT)
  val TRADING_DAYS_IN_YEAR: Integer = 252
  val TRADING_MONTHS_IN_YEAR: Integer = 12
  val RISK_FREE_RATE: Double = 0.06

  def main(args: Array[String]): Unit = {
    val fw = new FW
    val adjDailyCloses = (fw.getYHistData("AAPL", 20140501).col(ADJ_CLOSE).mapVec { x => x.map { x => x.toDouble } }).colAt(0)
    val sharpe = fw.sharpeDailyRet(adjDailyCloses)
    println(sharpe)
  }

}
class FW {
  def getYHistData(symbol: String, from: Integer): Frame[Date, String, String] = {
    val url = YFHistDataFetcherScala.getBaseUrl(symbol, from)
    val csvSource = new CsvUrl(url)
    CsvParser.parse(csvSource).withRowIndex(0).withColIndex(0).mapRowIndex { x => FW.YHIST_DATA_SDF.parse(x) }
  }

  def sharpeDailyRet(adjustedDailyCloses: Series[Date, Double]): Double = { sharpe(adjustedDailyCloses) }

  def sharpeMonthlyRet(adjustedMonthlyCloses: Series[Date, Double]): Double = { sharpe(adjustedMonthlyCloses, numOfTradingPeriodsInYear = FW.TRADING_MONTHS_IN_YEAR) }

  def sharpe(priceSeries: Series[Date, Double], riskFreeRate: Double = FW.RISK_FREE_RATE, numOfTradingPeriodsInYear: Integer = FW.TRADING_DAYS_IN_YEAR): Double = {
    val dailyReturns = returnize(priceSeries)
    val sharpe: Double = FastMath.sqrt(numOfTradingPeriodsInYear.toDouble) * (dailyReturns.mean - (riskFreeRate / numOfTradingPeriodsInYear)) / dailyReturns.stdev
    sharpe
  }

  def returnize(data: Series[Date, Double], returnPeriod: Integer = -1): Series[Date, Double] = {
    data / data.shift(returnPeriod) - 1
  }

}