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

import org.capnproto.SerializePacked;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import runutil.RunHelper;
import symbolaccess.NasdaqSymbolListParser;
import symbolaccess.NonNasdaqSymbolListParser;
import validation.Validation;
import validation.YFValidation;
import datalayer.helpers.CsvFileHelper;
import datalayer.objects.csvable.YFData;
import datalayer.objects.msg.converters.YFDataConverter;

/**
 * This thing just gets Publicly Available fundamental data from Yahoo and dumps to CSV/some other data container. It does nothing more nothing less and is not intended for commercial use.
 * 
 * @- TODO - There's unsafe calls all over the place. Clean them
 * @- TODO - No Validations anywhere at all. Add them
 * @- TODO - Alerts for bad data e.g. Yahoo thinks VTR has a Market Cap of 25.41 Trillion on 20150220
 */
public class YFDataFetcher
{
	public static final String SP500_FEB_2015_SYMBOL_FILE = "symbols.txt";
	public static final int SYMBOLS_PER_QUERY = 50;
	public static final int TRIES_PER_SYMBOL = 5;
	public static final long DELAY_BETWEEN_QUERIES_MILLIS = 5;

	public static void main(String[] args) throws Exception
	{
		// NSDQ listed symbols
		NasdaqSymbolListParser nsp = new NasdaqSymbolListParser();
		nsp.getSymbolListFile();
		Set<YFData> dataSet = getYahooFinData(nsp.parseFile());
		for (YFData data : dataSet)
			writeData(data);

		// Other listed symbols
		NonNasdaqSymbolListParser nnsp = new NonNasdaqSymbolListParser();
		nnsp.getSymbolListFile();
		dataSet = getYahooFinData(nnsp.parseFile());
		for (YFData data : dataSet)
			writeData(data);
	}

	public static Set<YFData> getYahooFinData(final Set<String> symbols)
	{
		Set<YFData> retFDataSet = null;
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
					for (int tries = 0; tries < TRIES_PER_SYMBOL && !symbolsForThisQuery.isEmpty(); ++tries)
					{
						System.out.println((tries + 1) + " ----- " + symbolsForThisQuery.size());
						try
						{
							InputStream is = YFDataFetcher.getYQXMLInputStream(symbolsForThisQuery);
							Set<YFData> processedSymbolData = YFDataFetcher.parseYQXMLQueryAndReturnSymbolData(is);
							if (processedSymbolData != null)
							{
								// Add to Return Set
								if (retFDataSet == null) retFDataSet = new HashSet<YFData>();
								retFDataSet.addAll(processedSymbolData);
								// Remove Processed Symbols
								for (YFData symbolData : processedSymbolData)
								{
									String symbol = symbolData.getSymbol();
									symbolsForThisQuery.remove(symbol);
								}
							}
							if (DELAY_BETWEEN_QUERIES_MILLIS > 0) Thread.sleep(DELAY_BETWEEN_QUERIES_MILLIS);
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					unprocessedSymbols.addAll(symbolsForThisQuery);
					symbolsForThisQuery.clear();
				}
			}
			for (String s : unprocessedSymbols)
			{
				System.out.println("Failed to process symbol - " + s);
			}
		}
		return retFDataSet;
	}

	public static Set<YFData> parseYQXMLQueryAndReturnSymbolData(InputStream inputStream) throws Exception
	{
		Set<YFData> processedSymbolData = null;
		if (inputStream != null)
		{
			// Create Document And Parse XML Stream
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder bd = dbf.newDocumentBuilder();
			Document dom = bd.parse(inputStream);

			// "query"
			// Element root = dom.getDocumentElement();
			// System.out.println(root.getAttribute("yahoo:created"));

			// "quote"
			NodeList quoteNodes = dom.getElementsByTagName("quote");
			if (quoteNodes != null && quoteNodes.getLength() > 0)
			{
				processedSymbolData = new HashSet<YFData>();
				for (int i = 0; i < quoteNodes.getLength(); ++i)
				{
					Element quote = (Element) quoteNodes.item(i);

					// CSV data - to be removed once capnp layer is ready
					YFData data = parseYDXMLQuote(quote);
					CsvFileHelper.writeAsCsvFile(RunHelper.getTodayRunDataDirectory(), data.getRowKey() + ".csv", ',', data); // TODO - don't write here

					// Add to processed symbols
					if (!Validation.isCatastrophicError(YFValidation.validate(data))) processedSymbolData.add(data);
				}
			}
		}
		return processedSymbolData;
	}

	public static YFData parseYDXMLQuote(Element quote)
	{
		YFData d = new YFData();
		d.setSymbol(getValueFromXML(quote, "Symbol"));
		d.setName(getValueFromXML(quote, "Name"));
		d.setStockExchange(getValueFromXML(quote, "StockExchange"));

		d.setYearLow(convertToDouble(getValueFromXML(quote, "YearLow")));
		d.setYearHigh(convertToDouble(getValueFromXML(quote, "YearHigh")));
		d.setChangeFromYearLow(convertToDouble(getValueFromXML(quote, "ChangeFromYearLow")));
		d.setChangeFromYearHigh(convertToDouble(getValueFromXML(quote, "ChangeFromYearHigh")));
		d.setPercentChangeFromYearLow(convertToDouble(getValueFromXML(quote, "PercentChangeFromYearLow")));
		d.setPercentChangeFromYearHigh(convertToDouble(getValueFromXML(quote, "PercebtChangeFromYearHigh"))); // Spelling mistake by Yahoo
		d.setFiftydayMovingAverage(convertToDouble(getValueFromXML(quote, "FiftydayMovingAverage")));
		d.setChangeFromFiftydayMovingAverage(convertToDouble(getValueFromXML(quote, "ChangeFromFiftydayMovingAverage")));
		d.setTwoHundreddayMovingAverage(convertToDouble(getValueFromXML(quote, "TwoHundreddayMovingAverage")));
		d.setChangeFromTwoHundreddayMovingAverage(convertToDouble(getValueFromXML(quote, "ChangeFromTwoHundreddayMovingAverage")));
		d.setAverageDailyVolume(convertToLong(getValueFromXML(quote, "AverageDailyVolume")));
		d.setPERatio(convertToDouble(getValueFromXML(quote, "PERatio")));
		d.setPEGRatio(convertToDouble(getValueFromXML(quote, "PEGRatio")));
		d.setPriceSales(convertToDouble(getValueFromXML(quote, "PriceSales")));
		d.setPriceBook(convertToDouble(getValueFromXML(quote, "PriceBook")));
		d.setEBITDA(convertToDouble(getValueFromXML(quote, "EBITDA")));
		d.setMarketCapitalization(convertToDouble(getValueFromXML(quote, "MarketCapitalization")));

		d.setEarningsShare(convertToDouble(getValueFromXML(quote, "EarningsShare")));
		d.setEPSEstimateCurrentYear(convertToDouble(getValueFromXML(quote, "EPSEstimateCurrentYear")));
		d.setEPSEstimateNextYear(convertToDouble(getValueFromXML(quote, "EPSEstimateNextYear")));
		d.setEPSEstimateNextQuarter(convertToDouble(getValueFromXML(quote, "EPSEstimateNextQuarter")));
		d.setDividendShare(convertToDouble(getValueFromXML(quote, "DividendShare")));
		d.setDividendYield(convertToDouble(getValueFromXML(quote, "DividendYield")));
		d.setExDividendDate(getValueFromXML(quote, "ExDividendDate"));
		d.setDividendPayDate(getValueFromXML(quote, "DividendPayDate"));
		d.setPriceEPSEstimateCurrentYear(convertToDouble(getValueFromXML(quote, "PriceEPSEstimateCurrentYear")));
		d.setPriceEPSEstimateNextYear(convertToDouble(getValueFromXML(quote, "PriceEPSEstimateNextYear")));
		d.setOneyrTargetPrice(convertToDouble(getValueFromXML(quote, "OneyrTargetPrice")));
		return d;
	}

	/**
	 * HELPERS
	 */
	public static String getValueFromXML(Element el, String tag)
	{
		NodeList nl = el.getElementsByTagName(tag);
		if (nl != null && nl.getLength() > 0) return nl.item(0).getTextContent();
		return null;
	}

	public static double convertToDouble(String value)
	{
		double d = 0.0;
		if (value != null && !value.isEmpty()) try
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
			else if (value.contains("K"))
			{
				value = value.replace("K", "");
				multiplier = 1000;
			}

			// Remove Percent (%) Character
			if (value.contains("%"))
			{
				value = value.replace("%", "");
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
		if (value != null && !value.isEmpty()) try
		{
			l = Long.valueOf(value);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		return l;
	}

	private static String getBaseUrl(final Set<String> symbols)
	{
		StringBuilder tickers = null;
		for (String symbol : symbols)
		{
			tickers = tickers == null ? tickers = new StringBuilder("\"" + symbol + "\"") : tickers.append(",\"" + symbol + "\"");
		}
		if (tickers != null)
		{
			StringBuilder baseUrl = new StringBuilder("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(");
			baseUrl.append(tickers);
			baseUrl.append(")&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
			return baseUrl.toString();
		}
		return null;
	}

	private static InputStream getYQXMLInputStream(final Set<String> symbols) throws Exception
	{
		// Make Connection And Request Data in XML Format
		URL url = new URL(getBaseUrl(symbols));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if (conn.getResponseCode() != 200) throw new IOException(conn.getResponseMessage());
		return conn.getInputStream();
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

	public static Set<String> readSymbolsFromFile() throws Exception
	{
		try (BufferedReader br = new BufferedReader(new FileReader(RunHelper.getCurrentWorkingDirectory() + SP500_FEB_2015_SYMBOL_FILE)))
		{
			Set<String> symbols = new HashSet<String>();
			String s = br.readLine();
			while (s != null)
			{
				symbols.add(s);
				s = br.readLine();
			}
			return symbols;
		}
	}

	public static void writeData(YFData data)
	{
		try
		{
			SerializePacked
					.writeToUnbuffered((new FileOutputStream(RunHelper.getTodayRunDataDirectory() + data.getSymbol() + ".data")).getChannel(), YFDataConverter.convert(data));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
