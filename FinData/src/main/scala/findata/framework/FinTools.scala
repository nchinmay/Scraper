package findata.framework

import java.text.SimpleDateFormat
import java.util.Date

import org.apache.commons.math3.util.FastMath
import org.saddle._
import org.saddle.index.OuterJoin
import org.saddle.io._

import findata.datafetching.YFHistDataFetcherScala
import org.saddle.index.RightJoin
import org.saddle.index.LeftJoin

import breeze.optimize._
import breeze.linalg.DenseVector

object FinTools {
  val ADJ_CLOSE = "Adj Close"
  val YHIST_DATA_SDF = new SimpleDateFormat(YFHistDataFetcherScala.YH_DATA_DATE_FORMAT)
  val TRADING_DAYS_IN_YEAR: Int = 252
  val TRADING_MONTHS_IN_YEAR: Int = 12
  val RISK_FREE_RATE: Double = 0.06

  def main(args: Array[String]): Unit = {
    val fw = new FinTools

    val symbols = Array("AAPL", "MSFT", "GOOGL", "TSLA", "FB")

    val from = 20130516

    // TODO - Why doesn't joining 2 Frame[Date, String, Double]s return a Frame[Date, String, Double]? 
    val adjCloseNorm: Frame[Date, String, Double] = fw.getAdjClosesNormalized(symbols, from)
    // println(adjCloseNorm)

    val dailyReturns = fw.returnizeFrame(adjCloseNorm)
    println(dailyReturns)

    val alloc = DenseVector(0.2, 0.2, 0.2, 0.2, 0.2)

    val f = new DiffFunction[DenseVector[Double]] {
      def calculate(x: DenseVector[Double]) = {
        val a = Vec(x.toArray)
        val sharpe = fw.sharpeDailyRet(dailyReturns, a)
        (sharpe, x)
      }
    }

    val lbfgs = new LBFGS[DenseVector[Double]](maxIter = 100, m = 3)

    val optimum = lbfgs.minimize(f, alloc)

    println(optimum)

    //    val largeRet = dailyReturns.colAt(0).filter { x => Math.abs(x) >= 0.005 }
    //    // println(largeRet)
    //
    //    val (l, r) = dailyReturns.shift(1).align(largeRet, RightJoin)
    //
    //    // val comp = dailyReturns.join(largeRet,OuterJoin)
    //
    //    println(l.length)
    //
    //    val add = l + r
    //    val absAdd = l.mapValues { x => Math.abs(x) } + r.mapValues { x => Math.abs(x) }
    //
    //    val success = (l < r).filter { x => x }
    //    val failure = (l < r).filter { x => !x }
    //    println("Wins ->" + success.length)
    //    println("Loses ->" + failure.length)
    //
    //    val absRet = (l - r).mapValues { x => Math.abs(x) }
    //    val profit = absRet.mask((l < r).toVec).sum
    //    val loss = absRet.mask((l >= r).toVec).sum
    //
    //    println("profit -> " + profit)
    //    println("loss -> " + loss)
    //    println("Net -> " + (profit - loss))
    //   val allocs = Vec(0.25, 0.25, 0.25, 0.25)
    //   val allocFrame = Frame(mat.repeat(allocs, adjReturnsNorm.rowIx.length, true)).setRowIndex(adjReturnsNorm.rowIx).setColIndex(adjReturnsNorm.colIx)
    //   val weightedAdjCloses = adjReturnsNorm.dot(allocFrame)
    //   println(weightedAdjCloses.head(5))
    //   println("SHARPE = " + fw.sharpeDailyRet(adjReturnsNorm, allocs))

    // Brute force optimize 4 stock portfolio, no constraints
    //    var bestSharpe: Double = 0
    //    var bestAlloc = Vec.empty[Double]
    //    for (i <- 1 to 100) {
    //      for (j <- 0 to 100 - i) {
    //        for (k <- 1 to 100 - i - j) {
    //          for (l <- 0 to 100 - i - j - k) {
    //            if (i + j + l + k == 100) {
    //              val alloc = Vec(i / 100.0, j / 100.0, k / 100.0, l / 100.0)
    //              val sharpe = fw.sharpeDailyRet(adjReturnsNorm, alloc)
    //              if (sharpe > bestSharpe) {
    //                bestSharpe = sharpe
    //                bestAlloc = alloc
    //              }
    //            }
    //          }
    //        }
    //      }
    //    }
    //    println("bestSharpe = " + bestSharpe)
    //    println("bestAlloc = " + bestAlloc)
  }
}
class FinTools {
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
    val adjDailyCloses = (getYHistData(symbol, from).col(FinTools.ADJ_CLOSE).setColIndex(Index(symbol)).mapVec { x => x.map { x => x.toDouble } })
    val adjClosesNormalized = adjDailyCloses.mapValues { x => x / adjDailyCloses.at(adjDailyCloses.rowIx.length - 1, 0) }
    adjClosesNormalized
  }

  /**
   * Getting Historical data from Yahoo
   */
  def getYHistData(symbol: String, from: Int): Frame[Date, String, String] = {
    val url = YFHistDataFetcherScala.getBaseUrl(symbol, from)
    val csvSource = CsvUrl(url)
    CsvParser.parse(csvSource).withRowIndex(0).withColIndex(0).mapRowIndex { x => FinTools.YHIST_DATA_SDF.parse(x) }
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
    sharpe(monthlyReturns, numOfTradingPeriodsInYear = FinTools.TRADING_MONTHS_IN_YEAR)
  }

  def sharpe(returnSeries: Series[Date, Double], riskFreeRate: Double = FinTools.RISK_FREE_RATE, numOfTradingPeriodsInYear: Integer = FinTools.TRADING_DAYS_IN_YEAR): Double = {
    val excessReturns = returnSeries - (riskFreeRate / numOfTradingPeriodsInYear)
    val sharpe: Double = FastMath.sqrt(numOfTradingPeriodsInYear.toDouble) * excessReturns.mean / excessReturns.stdev
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