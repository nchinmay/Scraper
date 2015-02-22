package datafetching;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.capnproto.MessageBuilder;
import org.capnproto.SerializePacked;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import datautil.CsvFileHelper;
import datautil.RunHelper;

/**
 * This thing just gets Publicly Available fundamental data from Yahoo and dumps
 * to CSV/some other data container. It does nothing more nothing less and is
 * not intended for commercial use.
 * 
 * @- TODO - There's unsafe calls all over the place. Clean them
 * @- TODO - No Validations anywhere at all. Add them
 * @- TODO - Alerts for bad data e.g. Yahoo thinks VTR has a Market Cap of 25.41
 *    Trillion on 20150220
 */
public class YDataFetcher
{
	public static final String SYMBOL_FILE = "symbols.txt";
	public static final int SYMBOLS_PER_QUERY = 50;
	public static final int TRIES_PER_SYMBOL = 3;

	public static void main(String[] args) throws Exception
	{
		Set<String> symbols = readSymbolsFromFile();
		getYahooData(symbols);
	}

	public static void getYahooData(final Set<String> symbols)
	{
		if (symbols != null)
		{
			Set<String> symbolsForThisQuery = new HashSet<String>();
			Set<String> unprocessedSymbols = new HashSet<String>();
			Iterator<String> iter = symbols.iterator();
			while (iter.hasNext())
			{
				symbolsForThisQuery.add(iter.next());
				if (symbolsForThisQuery.size() == SYMBOLS_PER_QUERY || !iter.hasNext())
				{
					try
					{
						for (int tries = 0; tries < TRIES_PER_SYMBOL && !symbolsForThisQuery.isEmpty(); ++tries)
						{
							InputStream is = YDataFetcher.getYQXMLInputStream(symbolsForThisQuery);
							Set<String> processedSymbols = YDataFetcher.parseYQXMLQueryAndReturnProcessedSymbols(is);
							if (processedSymbols != null)
								symbolsForThisQuery.removeAll(processedSymbols);
						}
						unprocessedSymbols.addAll(symbolsForThisQuery);
						symbolsForThisQuery.clear();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			for (String s : unprocessedSymbols)
			{
				System.out.println("Failed to process symbol - " + s);
			}
		}
	}

	public static String getBaseUrl(final Set<String> symbols)
	{
		StringBuilder tickers = null;
		for (String symbol : symbols)
		{
			tickers = tickers == null ? tickers = new StringBuilder("\"" + symbol + "\"") : tickers.append(",\""
					+ symbol + "\"");
		}
		if (tickers != null)
		{
			StringBuilder baseUrl = new StringBuilder(
					"http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(");
			baseUrl.append(tickers);
			baseUrl.append(")&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
			return baseUrl.toString();
		}
		return null;
	}

	public static InputStream getYQXMLInputStream(final Set<String> symbols) throws Exception
	{
		// Make Connection And Request Data in XML Format
		URL url = new URL(getBaseUrl(symbols));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if (conn.getResponseCode() != 200)
			throw new IOException(conn.getResponseMessage());
		return conn.getInputStream();
	}

	public static Set<String> parseYQXMLQueryAndReturnProcessedSymbols(InputStream inputStream) throws Exception
	{
		Set<String> processedSymbols = null;
		if (inputStream != null)
		{
			// Create Document And Parse XML Stream
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder bd = dbf.newDocumentBuilder();
			Document dom = bd.parse(inputStream);

			// "query"
			Element root = dom.getDocumentElement();
			System.out.println(root.getAttribute("yahoo:created"));

			// "quote"
			NodeList quoteNodes = dom.getElementsByTagName("quote");
			if (quoteNodes != null && quoteNodes.getLength() > 0)
			{
				processedSymbols = new HashSet<String>();
				for (int i = 0; i < quoteNodes.getLength(); ++i)
				{
					Element quote = (Element) quoteNodes.item(i);

					// CSV data - to be removed once capnp layer is ready
					YData data = parseYDXMLQuote(quote);
					CsvFileHelper.writeAsCsvFile(RunHelper.getTodayRunDataDirectory(), data.getRowKey() + ".csv", ',',
							data);

					// Cap'n Proto data
					parseYDXMLQuoteToCapnp(quote);

					// Add to processed symbols
					processedSymbols.add(data.getSymbol());
					System.out.println(data);
				}
			}
		}
		return processedSymbols;
	}

	public static YData parseYDXMLQuote(Element quote)
	{
		YData d = new YData();
		d.setSymbol(getValueFromXML(quote, "Symbol"));
		d.setName(getValueFromXML(quote, "Name"));

		d.setYearLow(convertToDouble(getValueFromXML(quote, "YearLow")));
		d.setYearHigh(convertToDouble(getValueFromXML(quote, "YearHigh")));
		d.setFiftydayMovingAverage(convertToDouble(getValueFromXML(quote, "FiftydayMovingAverage")));
		d.setChangeFromFiftydayMovingAverage(convertToDouble(getValueFromXML(quote, "ChangeFromFiftydayMovingAverage")));
		d.setTwoHundreddayMovingAverage(convertToDouble(getValueFromXML(quote, "TwoHundreddayMovingAverage")));
		d.setChangeFromTwoHundreddayMovingAverage(convertToDouble(getValueFromXML(quote,
				"ChangeFromTwoHundreddayMovingAverage")));
		d.setAverageDailyVolume(convertToLong(getValueFromXML(quote, "AverageDailyVolume")));
		d.setPERatio(convertToDouble(getValueFromXML(quote, "PERatio")));
		d.setPEGRatio(convertToDouble(getValueFromXML(quote, "PEGRatio")));
		d.setEBITDA(convertToDouble(getValueFromXML(quote, "EBITDA")));
		d.setMarketCapitalization(convertToDouble(getValueFromXML(quote, "MarketCapitalization")));

		d.setEarningsShare(convertToDouble(getValueFromXML(quote, "EarningsShare")));
		d.setEPSEstimateCurrentYear(convertToDouble(getValueFromXML(quote, "EPSEstimateCurrentYear")));
		d.setEPSEstimateNextYear(convertToDouble(getValueFromXML(quote, "EPSEstimateNextYear")));
		d.setEPSEstimateNextQuarter(convertToDouble(getValueFromXML(quote, "EPSEstimateNextQuarter")));
		d.setDividendShare(convertToDouble(getValueFromXML(quote, "DividendShare")));
		d.setDividendYield(convertToDouble(getValueFromXML(quote, "DividendYield")));
		d.setExDividendDate(getValueFromXML(quote, "ExDividendDate"));
		d.setPriceEPSEstimateCurrentYear(convertToDouble(getValueFromXML(quote, "PriceEPSEstimateCurrentYear")));
		d.setPriceEPSEstimateNextYear(convertToDouble(getValueFromXML(quote, "PriceEPSEstimatenexttYear")));
		d.setOneyrTargetPrice(convertToDouble(getValueFromXML(quote, "OneyrTargetPrice")));
		return d;
	}

	public static MessageBuilder parseYDXMLQuoteToCapnp(Element quote)
	{
		// Cap'n Proto data
		MessageBuilder message = new MessageBuilder();
		datafetching.YDataCP.YData.Builder db = message.initRoot(datafetching.YDataCP.YData.factory);
		db.setSymbol(getValueFromXML(quote, "Symbol"));
		db.setName(getValueFromXML(quote, "Name"));

		db.setYearLow(convertToDouble(getValueFromXML(quote, "YearLow")));
		db.setYearHigh(convertToDouble(getValueFromXML(quote, "YearHigh")));
		db.setFiftydayMovingAverage(convertToDouble(getValueFromXML(quote, "FiftydayMovingAverage")));
		db.setChangeFromFiftydayMovingAverage(convertToDouble(getValueFromXML(quote, "ChangeFromFiftydayMovingAverage")));
		db.setTwoHundreddayMovingAverage(convertToDouble(getValueFromXML(quote, "TwoHundreddayMovingAverage")));
		db.setChangeFromTwoHundreddayMovingAverage(convertToDouble(getValueFromXML(quote,
				"ChangeFromTwoHundreddayMovingAverage")));
		db.setAverageDailyVolume(convertToLong(getValueFromXML(quote, "AverageDailyVolume")));
		db.setPERatio(convertToDouble(getValueFromXML(quote, "PERatio")));
		db.setPEGRatio(convertToDouble(getValueFromXML(quote, "PEGRatio")));
		db.setEBITDA(convertToDouble(getValueFromXML(quote, "EBITDA")));
		db.setMarketCapitalization(convertToDouble(getValueFromXML(quote, "MarketCapitalization")));

		db.setEarningsShare(convertToDouble(getValueFromXML(quote, "EarningsShare")));
		db.setEPSEstimateCurrentYear(convertToDouble(getValueFromXML(quote, "EPSEstimateCurrentYear")));
		db.setEPSEstimateNextYear(convertToDouble(getValueFromXML(quote, "EPSEstimateNextYear")));
		db.setEPSEstimateNextQuarter(convertToDouble(getValueFromXML(quote, "EPSEstimateNextQuarter")));
		db.setDividendShare(convertToDouble(getValueFromXML(quote, "DividendShare")));
		db.setDividendYield(convertToDouble(getValueFromXML(quote, "DividendYield")));
		db.setExDividendDate(getValueFromXML(quote, "ExDividendDate"));
		db.setPriceEPSEstimateCurrentYear(convertToDouble(getValueFromXML(quote, "PriceEPSEstimateCurrentYear")));
		db.setPriceEPSEstimateNextYear(convertToDouble(getValueFromXML(quote, "PriceEPSEstimatenexttYear")));
		db.setOneyrTargetPrice(convertToDouble(getValueFromXML(quote, "OneyrTargetPrice")));

		try
		{
			SerializePacked.writeToUnbuffered((new FileOutputStream(RunHelper.getTodayRunDataDirectory() + db.getSymbol()
					+ ".data")).getChannel(), message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return message;
	}

	/**
	 * HELPERS
	 */
	public static String getValueFromXML(Element el, String tag)
	{
		NodeList nl = el.getElementsByTagName(tag);
		if (nl != null && nl.getLength() > 0)
			return nl.item(0).getTextContent();
		return null;
	}

	public static Set<String> readSymbolsFromFile() throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(RunHelper.getCurrentRunDirectory() + SYMBOL_FILE));
		Set<String> symbols = new HashSet<String>();

		String s = br.readLine();
		while (s != null)
		{
			symbols.add(s);
			s = br.readLine();
		}
		br.close();
		return symbols;
	}

	public static double convertToDouble(String value)
	{
		double d = 0.0;
		if (value != null && !value.isEmpty())
			try
			{
				double multiplier = 1.0;
				if (value.contains("B"))
				{
					value = value.replace("B", "");
					multiplier = 1000000000;
				}
				else if (value.contains("M"))
				{
					value = value.replace("M", "");
					multiplier = 1000000;
				}
				d = Double.valueOf(value) * multiplier;
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
		return d;
	}

	public static long convertToLong(String value)
	{
		long l = 0;
		if (value != null && !value.isEmpty())
			try
			{
				l = Long.valueOf(value);
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
		return l;
	}

	/**
	 * TEST STUFF
	 */
	public static Set<String> getTestSymbols()
	{
		Set<String> symbols = new HashSet<String>();
		symbols.add("AAPL");
		symbols.add("GOOG");
		return symbols;
	}
}
