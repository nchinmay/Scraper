package datafetching;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class YDataFetcher {
	public static void main(String[] args) throws Exception {
		Set<String> symbols = getTestSymbols();
		InputStream is = YDataFetcher.getYQXMLData(symbols);
		YDataFetcher.parseYQXMLQuery(is);
	}

	public static String getBaseUrl(Set<String> symbols) {
		StringBuilder tickers = null;
		for (String symbol : symbols) {
			tickers = tickers == null ? tickers = new StringBuilder("\""
					+ symbol + "\"") : tickers.append(",\"" + symbol + "\"");
		}
		if (tickers != null) {
			StringBuilder baseUrl = new StringBuilder(
					"http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(");
			baseUrl.append(tickers);
			baseUrl.append(")&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
			return baseUrl.toString();
		}
		return null;
	}

	public static InputStream getYQXMLData(final Set<String> symbols)
			throws Exception {
		// Make Connection And Request Data in XML Format
		URL url = new URL(getBaseUrl(symbols));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if (conn.getResponseCode() != 200)
			throw new IOException(conn.getResponseMessage());
		return conn.getInputStream();
	}

	public static void parseYQXMLQuery(InputStream inputStream)
			throws Exception {
		if (inputStream != null) {
			// Create Document And Parse XML Stream
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder bd = dbf.newDocumentBuilder();
			Document dom = bd.parse(inputStream);

			// "query"
			Element root = dom.getDocumentElement();
			System.out.println(root.getAttribute("yahoo:created"));

			// "quote"
			NodeList quoteNodes = dom.getElementsByTagName("quote");
			if (quoteNodes != null && quoteNodes.getLength() > 0) {
				for (int i = 0; i < quoteNodes.getLength(); ++i) {
					YData data = parseYDXMLQuote((Element) quoteNodes.item(i));
					System.out.println(data);
				}
			}
		}
	}

	public static YData parseYDXMLQuote(Element quote) {
		YData d = new YData();
		d.setSymbol(getValue(quote, "Symbol"));
		d.setYearLow(getValue(quote, "YearLow"));
		d.setYearHigh(getValue(quote, "YearHigh"));
		d.setAverageDailyVolume(getValue(quote, "AverageDailyVolume"));
		d.setpERatio(getValue(quote, "PERatio"));
		d.setpEGRatio(getValue(quote, "PEGRatio"));
		d.seteBITDA(getValue(quote, "EBITDA"));
		d.setMarketCapitalization(getValue(quote, "MarketCapitalization"));
		d.setEarningsShare(getValue(quote, "EarningsShare"));
		d.setePSEstimateCurrentYear(getValue(quote, "EPSEstimateCurrentYear"));
		d.setePSEstimateNextYear(getValue(quote, "EPSEstimateNextYear"));
		d.setePSEstimateNextQuarter(getValue(quote, "EPSEstimateNextQuarter"));
		d.setPriceEPSEstimateCurrentYear(getValue(quote,
				"PriceEPSEstimateCurrentYear"));
		d.setPriceEPSEstimateNextYear(getValue(quote,
				"PriceEPSEstimatenexttYear"));
		d.setOneyrTargetPrice(getValue(quote, "OneyrTargetPrice"));

		return d;
	}

	public static String getValue(Element el, String tag) {
		NodeList nl = el.getElementsByTagName(tag);
		if (nl != null && nl.getLength() > 0)
			return nl.item(0).getTextContent();
		return null;
	}

	public static Set<String> getTestSymbols() {
		Set<String> symbols = new HashSet<String>();
		symbols.add("AAPL");
		symbols.add("GOOG");
		return symbols;
	}
}
