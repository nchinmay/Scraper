package findata.datafetching

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class YFHistDataFetcherScalaTest extends FunSuite {

  test("Test getBaseUrl(ticker: String)") {
    val expectedUrl = YFHistDataFetcherScala.YF_ICHART_URL_PREFIX + "AAPL&a=0&b=1" + YFHistDataFetcherScala.YF_ICHART_URL_SUFFIX
    assert(YFHistDataFetcherScala.getBaseUrl("AAPL") === expectedUrl)
  }

  test("Test getBaseUrl(ticker: String, from: Int)") {
    val expectedUrl = YFHistDataFetcherScala.YF_ICHART_URL_PREFIX + "AAPL&a=2&b=17&c=2015"
    assert(YFHistDataFetcherScala.getBaseUrl("AAPL", 20150317) === expectedUrl)
  }
}