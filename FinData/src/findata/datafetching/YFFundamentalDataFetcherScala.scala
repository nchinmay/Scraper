package findata.datafetching

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

  val in = scala.io.Source.fromURL(getBaseUrl(Set("AAPL")), "utf-8")
  for (line <- in.getLines)
    println(line)
}