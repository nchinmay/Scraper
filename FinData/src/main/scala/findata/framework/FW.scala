package findata.framework

import findata.datafetching.YFHistDataFetcherScala
import org.saddle.io.CsvParser

object FW {
  def main(args: Array[String]): Unit = {
    val url = YFHistDataFetcherScala.getBaseUrl("AAPL", 20150301)
    val csvSource = new CsvUrl(url)
    val df = CsvParser.parse(csvSource).withRowIndex(0).withColIndex(0)
    println(df)
  }
}