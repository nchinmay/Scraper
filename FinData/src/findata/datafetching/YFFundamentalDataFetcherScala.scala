package findata.datafetching

import scala.collection.mutable.Set
import scala.xml.Node
import scala.xml.XML

import datalayer.objects.findata.YFFundamentalData

/**
 * This thing just gets Publicly Available fundamental data from Yahoo and dumps to CSV/some other data container. It does nothing more nothing less and is not intended for commercial use.
 *
 * @- TODO - There's unsafe calls all over the place. Clean them
 * @- TODO - No Validations anywhere at all. Add them
 * @- TODO - Alerts for bad data e.g. Yahoo thinks VTR has a Market Cap of 25.41 Trillion on 20150220
 */

class YFFundamentalDataFetcherScala {

  val SP500_FEB_2015_SYMBOL_FILE = "symbols.txt";
  val SYMBOLS_PER_QUERY = 50;
  val TRIES_PER_SYMBOL = 5;
  val DELAY_BETWEEN_QUERIES_MILLIS = 5;

  val in = scala.io.Source.fromURL(getBaseUrl(Set("AAPL")), "utf-8").mkString
  val xml = XML.loadString(in)

  def getYFFData(symbols: Set[String]): Set[YFFundamentalData] = {
    val ret: Set[YFFundamentalData] = Set()
    for (currentSet <- symbols.grouped(SYMBOLS_PER_QUERY)) {

      // TODO -- try 3 times per symbol to get values
      val queryResultXML = XML.loadString(scala.io.Source.fromURL(getBaseUrl(currentSet), "utf-8").mkString)
      for (resultsNode <- queryResultXML) {
        val yffdFromQuery = parseQuery(resultsNode.child(0))
        ret ++= yffdFromQuery
      }
      // TODO -- try 3 times per symbol to get values
    }
    ret
  }

  def parseQuery(resultsNode: Node): Set[YFFundamentalData] = {
    val ret: Set[YFFundamentalData] = Set()
    for (quoteNode <- resultsNode.child) {
      val yffd = new YFFundamentalData()
      for (prop <- quoteNode.child) {
        prop.label match {
          case "Symbol"                               => yffd.setSymbol(prop.text)
          case "Name"                                 => yffd.setName(prop.text)
          case "StockExchange"                        => yffd.setStockExchange(prop.text)

          case "YearLow"                              => yffd.setYearLow(parseDouble(prop.text))
          case "YearHigh"                             => yffd.setYearHigh(parseDouble(prop.text))
          case "ChangeFromYearLow"                    => yffd.setChangeFromYearLow(parseDouble(prop.text))
          case "ChangeFromYearHigh"                   => yffd.setChangeFromYearHigh(parseDouble(prop.text))
          case "PercentChangeFromYearLow"             => yffd.setPercentChangeFromYearLow(parseDouble(prop.text))
          case "PercebtChangeFromYearHigh"            => yffd.setPercentChangeFromYearHigh(parseDouble(prop.text))
          case "FiftydayMovingAverage"                => yffd.setFiftydayMovingAverage(parseDouble(prop.text))
          case "ChangeFromFiftydayMovingAverage"      => yffd.setChangeFromFiftydayMovingAverage(parseDouble(prop.text))
          case "TwoHundreddayMovingAverage"           => yffd.setTwoHundreddayMovingAverage(parseDouble(prop.text))
          case "ChangeFromTwoHundreddayMovingAverage" => yffd.setChangeFromTwoHundreddayMovingAverage(parseDouble(prop.text))
          case "AverageDailyVolume"                   => yffd.setAverageDailyVolume(parseLong(prop.text))
          case "PERatio"                              => yffd.setPERatio(parseDouble(prop.text))
          case "PEGRatio"                             => yffd.setPEGRatio(parseDouble(prop.text))
          case "PriceSales"                           => yffd.setPriceSales(parseDouble(prop.text))
          case "PriceBook"                            => yffd.setPriceBook(parseDouble(prop.text))
          case "EBITDA"                               => yffd.setEBITDA(parseDouble(prop.text))
          case "MarketCapitalization"                 => yffd.setMarketCapitalization(parseDouble(prop.text))

          case "EarningsShare"                        => yffd.setEarningsShare(parseDouble(prop.text))
          case "EPSEstimateCurrentYear"               => yffd.setEPSEstimateCurrentYear(parseDouble(prop.text))
          case "EPSEstimateNextYear"                  => yffd.setEPSEstimateNextYear(parseDouble(prop.text))
          case "EPSEstimateNextQuarter"               => yffd.setEPSEstimateNextQuarter(parseDouble(prop.text))
          case "DividendShare"                        => yffd.setDividendShare(parseDouble(prop.text))
          case "DividendYield"                        => yffd.setDividendYield(parseDouble(prop.text))
          case "ExDividendDate"                       => yffd.setExDividendDate(prop.text)
          case "DividendPayDate"                      => yffd.setDividendPayDate(prop.text)
          case "PriceEPSEstimateCurrentYear"          => yffd.setPriceEPSEstimateCurrentYear(parseDouble(prop.text))
          case "PriceEPSEstimateNextYear"             => yffd.setPriceEPSEstimateNextYear(parseDouble(prop.text))
          case "OneyrTargetPrice"                     => yffd.setOneyrTargetPrice(parseDouble(prop.text))

          case _                                      =>
        }
        ret += yffd
      }
    }
    ret
  }

  def parseDouble(d: String): Double = {
    try {
      if (d.isEmpty()) 0
      else if (d.endsWith("B")) 1000000000 * d.dropRight(1).toDouble
      else if (d.endsWith("M")) 1000000 * d.dropRight(1).toDouble
      else if (d.endsWith("K")) 1000 * d.dropRight(1).toDouble
      else if (d.endsWith("%")) d.dropRight(1).toDouble
      else d.toDouble
    } catch {
      case e: NumberFormatException => 0
    }
  }

  def parseLong(l: String): Long = {
    try {
      if (l.isEmpty()) 0
      else l.toLong
    } catch {
      case e: NumberFormatException => 0
    }
  }

  def getBaseUrl(symbols: Set[String]): String = {
    return "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(" +
      symbols.mkString("\"", "\",\"", "\"") + ")&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
  }
}