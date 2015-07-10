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

  def getBaseUrl(symbols: Set[String]): String = {
    return "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(" +
      symbols.mkString("\"", "\",\"", "\"") + ")&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"
  }

  val in = scala.io.Source.fromURL(getBaseUrl(Set("AAPL")), "utf-8").mkString
  val xml = XML.loadString(in)

  def parseQuery(queryNode: Node): Set[YFFundamentalData] = {
    val ret: Set[YFFundamentalData] = Set()
    for (child <- queryNode.child) {
      val yffd = new YFFundamentalData()
      child.label match {
        case "Symbol"                               => yffd.setSymbol(child.text)
        case "Name"                                 => yffd.setName(child.text)
        case "StockExchange"                        => yffd.setStockExchange(child.text)

        case "YearLow"                              => yffd.setYearLow(child.text.toDouble)
        case "YearHigh"                             => yffd.setYearHigh(child.text.toDouble)
        case "ChangeFromYearLow"                    => yffd.setChangeFromYearLow(child.text.toDouble)
        case "ChangeFromYearHigh"                   => yffd.setChangeFromYearHigh(child.text.toDouble)
        case "PercentChangeFromYearLow"             => yffd.setPercentChangeFromYearLow(child.text.replace("%", "").toDouble)
        case "PercebtChangeFromYearHigh"            => yffd.setPercentChangeFromYearHigh(child.text.replace("%", "").toDouble)
        case "FiftydayMovingAverage"                => yffd.setFiftydayMovingAverage(child.text.toDouble)
        case "ChangeFromFiftydayMovingAverage"      => yffd.setChangeFromFiftydayMovingAverage(child.text.replace("%", "").toDouble)
        case "TwoHundreddayMovingAverage"           => yffd.setTwoHundreddayMovingAverage(child.text.toDouble)
        case "ChangeFromTwoHundreddayMovingAverage" => yffd.setChangeFromTwoHundreddayMovingAverage(child.text.replace("%", "").toDouble)
        case "AverageDailyVolume"                   => yffd.setAverageDailyVolume(child.text.toLong)
        case "PERatio"                              => yffd.setPERatio(child.text.toDouble)
        case "PEGRatio"                             => yffd.setPEGRatio(child.text.toDouble)
        case "PriceSales"                           => yffd.setPriceSales(child.text.toDouble)
        case "PriceBook"                            => yffd.setPriceBook(child.text.toDouble)
        case "EBITDA"                               => yffd.setEBITDA(child.text.toDouble)
        case "MarketCapitalization"                 => yffd.setMarketCapitalization(child.text.toDouble)

        case "EarningsShare"                        => yffd.setEarningsShare(child.text.toDouble)
        case "EPSEstimateCurrentYear"               => yffd.setEPSEstimateCurrentYear(child.text.toDouble)
        case "EPSEstimateNextYear"                  => yffd.setEPSEstimateNextYear(child.text.toDouble)
        case "EPSEstimateNextQuarter"               => yffd.setEPSEstimateNextQuarter(child.text.toDouble)
        case "DividendShare"                        => yffd.setDividendShare(child.text.toDouble)
        case "DividendYield"                        => yffd.setDividendYield(child.text.replace("%", "").toDouble)
        case "ExDividendDate"                       => yffd.setExDividendDate(child.text)
        case "DividendPayDate"                      => yffd.setDividendPayDate(child.text)
        case "PriceEPSEstimateCurrentYear"          => yffd.setPriceEPSEstimateCurrentYear(child.text.toDouble)
        case "PriceEPSEstimateNextYear"             => yffd.setPriceEPSEstimateNextYear(child.text.toDouble)
        case "OneyrTargetPrice"                     => yffd.setOneyrTargetPrice(child.text.toDouble)

        case _                                      =>
      }
      ret.add(yffd)
    }
    ret
  }
}