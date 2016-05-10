package findata.framework

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.commons.math3.util.FastMath
import org.saddle._
import org.saddle.index.OuterJoin
import org.saddle.io._

import findata.datafetching.YFHistDataFetcherScala

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
    val ret: Frame[Date, String, Double] = fw.getAdjClosesNormalized(symbols, from)
    println(ret)

    val allocs = Vec(0.25, 0.25, 0.25, 0.25)
    val allocFrame = Frame(mat.repeat(allocs, ret.rowIx.length, true)).setRowIndex(ret.rowIx).setColIndex(ret.colIx)

    val weightedAdjCloses = ret.dot(allocFrame)
    println(weightedAdjCloses)

    println("SHARPE = " + fw.sharpeDailyRet(ret, allocs))

    // val sharpe = fw.sharpeDailyRet(adjDailyCloses)
    // println(sharpe)
  }

}
class FW {
  /**
   * Adjusted Closes
   */
  def getAdjClosesNormalized(symbols: Array[String], from: Int): Frame[Date, String, Double] = {
    val adjClosesNorm = symbols.foldLeft(Frame.empty[Date, Int, Double]) {
      (prevFrame, symbol) => prevFrame.join(getAdjClosesNormalized(symbol, from).resetColIndex, OuterJoin)
    }.setColIndex(Index(symbols))
    adjClosesNorm
  }

  def getAdjClosesNormalized(symbol: String, from: Int): Frame[Date, String, Double] = {
    val adjDailyCloses = (getYHistData(symbol, from).col(FW.ADJ_CLOSE).setColIndex(Index(symbol)).mapVec { x => x.map { x => x.toDouble } })
    val adjClosesNormalized = adjDailyCloses.mapValues { x => x / adjDailyCloses.at(adjDailyCloses.rowIx.length - 1, 0) }
    adjClosesNormalized
  }

  /**
   * Getting Historical data from Yahoo
   */
  def getYHistData(symbol: String, from: Int): Frame[Date, String, String] = {
    val url = YFHistDataFetcherScala.getBaseUrl(symbol, from)
    val csvSource = CsvUrl(url)
    CsvParser.parse(csvSource).withRowIndex(0).withColIndex(0).mapRowIndex { x => FW.YHIST_DATA_SDF.parse(x) }
  }

  /**
   * Sharpe Ratio for Normalized Adj Closes Frame with portfolio allocations `weights`
   */
  def sharpeDailyRet(adjClosesNormalized: Frame[Date, String, Double], weights: Vec[Double]): Double = {
    require(adjClosesNormalized.colIx.length == weights.length, "adjClosesNormalized.colIx.length should equal allocs.length")
    val allocFrame = Frame(mat.repeat(weights, adjClosesNormalized.rowIx.length, true))
      .setRowIndex(adjClosesNormalized.rowIx)
      .setColIndex(adjClosesNormalized.colIx)
    val weightedAdjCloses = adjClosesNormalized.dot(allocFrame)
    sharpeDailyRet(weightedAdjCloses)
  }

  /**
   * Sharpe Ratio for Individual Adj Closes Series
   */
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

  /**
   * Calculate Return for a Series over `returnPeriod`
   */
  def returnizeSeries(dataSeries: Series[Date, Double], returnPeriod: Int = -1): Series[Date, Double] = {
    dataSeries / dataSeries.shift(returnPeriod) - 1
  }

  /**
   * Calculate Return for a Frame over `returnPeriod`
   */
  def returnizeFrame(dataFrame: Frame[Date, String, Double], returnPeriod: Int = -1): Frame[Date, String, Double] = {
    dataFrame / dataFrame.shift(returnPeriod) - 1
  }

}