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
import org.saddle.Index
import org.saddle.index.OuterJoin
import org.saddle.Vec

object FW {
  val ADJ_CLOSE = "Adj Close"
  val YHIST_DATA_SDF = new SimpleDateFormat(YFHistDataFetcherScala.YH_DATA_DATE_FORMAT)
  val TRADING_DAYS_IN_YEAR: Int = 252
  val TRADING_MONTHS_IN_YEAR: Int = 12
  val RISK_FREE_RATE: Double = 0.06

  def main(args: Array[String]): Unit = {
    val fw = new FW

    val symbols = Array("AAPL", "GOOG", "MSFT", "TSLA")
    val from = 20140501

    // TODO - Why doesn't joining 2 Frame[Date, String, Double]s return a Frame[Date, String, Double]? 
    var ret: Frame[Date, String, Double] = fw.getAdjClosesNormalized(symbols, from)
    println(ret)

    val allocs = Frame(Vec(0.25, 0.25, 0.25, 0.25), ret.rowIx, ret.colIx)
println( allocs)
   // val weightedRet = ret.dot(allocs)
    // val sharpe = fw.sharpeDailyRet(adjDailyCloses)
    // println(sharpe)
  }

}
class FW {
  def getAdjClosesNormalized(symbols: Array[String], from: Int): Frame[Date, String, Double] = {
    var ret: Frame[Date, Int, Double] = null
    for (symbol <- symbols) {
      if (ret == null) ret = getAdjClosesNormalized(symbol, from).resetColIndex
      else ret = ret.join(getAdjClosesNormalized(symbol, from), OuterJoin)
    }
    ret.setColIndex(Index(symbols))
  }

  def getAdjClosesNormalized(symbol: String, from: Int): Frame[Date, String, Double] = {
    val adjDailyCloses = (getYHistData(symbol, from).col(FW.ADJ_CLOSE).setColIndex(Index(symbol)).mapVec { x => x.map { x => x.toDouble } })
    val adjClosesNormalized = adjDailyCloses.mapValues { x => x / adjDailyCloses.at(adjDailyCloses.rowIx.length - 1, 0) }
    adjClosesNormalized
  }

  def getYHistData(symbol: String, from: Int): Frame[Date, String, String] = {
    val url = YFHistDataFetcherScala.getBaseUrl(symbol, from)
    val csvSource = new CsvUrl(url)
    CsvParser.parse(csvSource).withRowIndex(0).withColIndex(0).mapRowIndex { x => FW.YHIST_DATA_SDF.parse(x) }
  }

  def sharpeDailyRet(adjustedDailyCloses: Series[Date, Double]): Double = {
    val dailyReturns = returnizeSeries(adjustedDailyCloses)
    sharpe(dailyReturns)
  }

  def sharpeMonthlyRet(adjustedMonthlyCloses: Series[Date, Double]): Double = {
    val monthlyReturns = returnizeSeries(adjustedMonthlyCloses)
    sharpe(monthlyReturns, numOfTradingPeriodsInYear = FW.TRADING_MONTHS_IN_YEAR)
  }

  def sharpe(returnSeries: Series[Date, Double], riskFreeRate: Double = FW.RISK_FREE_RATE, numOfTradingPeriodsInYear: Integer = FW.TRADING_DAYS_IN_YEAR): Double = {
    val sharpe: Double = FastMath.sqrt(numOfTradingPeriodsInYear.toDouble) * (returnSeries.mean - (riskFreeRate / numOfTradingPeriodsInYear)) / returnSeries.stdev
    sharpe
  }

  def returnizeSeries(dataSeries: Series[Date, Double], returnPeriod: Int = -1): Series[Date, Double] = {
    dataSeries / dataSeries.shift(returnPeriod) - 1
  }

  def returnizeFrame(dataFrame: Frame[Date, String, Double], returnPeriod: Int = -1): Frame[Date, String, Double] = {
    dataFrame / dataFrame.shift(returnPeriod) - 1
  }

}