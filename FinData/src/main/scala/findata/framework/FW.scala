package findata.framework

import findata.datafetching.YFHistDataFetcherScala
import org.saddle.io.CsvParser
import org.joda.time.DateTime
import java.util.Formatter.DateTime
import java.text.SimpleDateFormat
import org.saddle.Frame
import java.util.Date
import org.apache.commons.math3.util.FastMath

object FW {
  val ADJ_CLOSE = "Adj Close"
  val YHIST_DATA_SDF = new SimpleDateFormat("yyyy-MM-dd")
  val TRADING_DAYS_IN_YEAR: Integer = 252
  val TRADING_MONTHS_IN_YEAR: Integer = 12
  val RISK_FREE_RETURN: Double = 0.06

  def main(args: Array[String]): Unit = {
    val fw = new FW
    val adjClose = fw.getYHistData("AAPL", 20140501).col(ADJ_CLOSE).mapVec { x => x.map { x => x.toDouble } }
    println(adjClose)
    val shiftedAdjClose = adjClose.shift(-1)
    println(shiftedAdjClose)
    val returns = (adjClose / shiftedAdjClose - 1)
    println(returns)
    val sharpe: Double = FastMath.sqrt(FW.TRADING_DAYS_IN_YEAR.toDouble) * (returns.colAt(0).mean - (RISK_FREE_RETURN / TRADING_DAYS_IN_YEAR)) / returns.colAt(0).stdev
    println(sharpe)
  }

}
class FW {
  def getYHistData(symbol: String, from: Integer): Frame[Date, String, String] = {
    val url = YFHistDataFetcherScala.getBaseUrl(symbol, from)
    val csvSource = new CsvUrl(url)
    CsvParser.parse(csvSource).withRowIndex(0).withColIndex(0).mapRowIndex { x => FW.YHIST_DATA_SDF.parse(x) }
  }

  //def returnize0(Frame[String, String, Double] f)  = {1}

}