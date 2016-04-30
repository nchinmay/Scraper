package findata.datafetching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.function.Function;
import java.util.stream.Stream;

import datalayer.objects.findata.YFHistData;
import findata.helpers.NetAccessHelper;

public class YFHistDataFetcher
{
	public static final int YH_DATA_START_DATE = 19620001;
	public static final String YH_DATA_SEPERATOR = ",";
	public static final String YH_DATA_DATE_FORMAT = "yyyy-MM-dd";
	public static final SimpleDateFormat YH_DATA_DATE_FORMATTER = new SimpleDateFormat(YH_DATA_DATE_FORMAT);

	public static Stream<String> getHistDataStream(String symbol) throws Exception
	{
		return getHistDataStream(symbol, YH_DATA_START_DATE);
	}

	public static Stream<String> getHistDataStream(String symbol, int fromDate) throws Exception
	{
		BufferedReader buffer = (new BufferedReader(new InputStreamReader(NetAccessHelper.getInputStreamFromUrl(getBaseUrl(symbol, fromDate)))));
		buffer.readLine(); // Skip Header - Date,Open,High,Low,Close,Volume,Adj Close
		return buffer.lines();
	}

	public static Stream<YFHistData> getSortedHistDataStream(String symbol) throws Exception
	{
		return getSortedHistDataStream(symbol, YH_DATA_START_DATE);
	}

	public static Stream<YFHistData> getSortedHistDataStream(String symbol, int fromDate) throws Exception
	{
		return getHistDataStream(symbol, fromDate).map(new Function<String, YFHistData>() {
			@Override
			public YFHistData apply(String t)
			{
				YFHistData d = null;
				try
				{
					String[] parts = t.split(YH_DATA_SEPERATOR);
					d = new YFHistData();
					d.setSymbol(symbol);
					d.setDate(Integer.parseInt(parts[0].replace("-", "")));
					d.setOpen(Double.parseDouble(parts[1]));
					d.setHigh(Double.parseDouble(parts[2]));
					d.setLow(Double.parseDouble(parts[3]));
					d.setClose(Double.parseDouble(parts[4]));
					d.setAdv(Long.parseLong(parts[5]));
					d.setAdjClose(Double.parseDouble(parts[6]));
					return d;
				}
				catch (Exception e)
				{
					System.out.println("Failed to parse - " + t);
				}
				return d;
			}
		}).sorted();
	}

	public static String getBaseUrl(String symbol)
	{
		return "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&c=1962";
	}

	public static String getBaseUrl(String symbol, int fromDate)
	{
		String fromDateStr = String.valueOf(fromDate);
		if (fromDateStr.length() != 8) return null;
		int fromYear = Integer.parseInt(fromDateStr.substring(0, 4));
		int fromMonth = Integer.parseInt(fromDateStr.substring(4, 6)) - 1;
		int fromDay = Integer.parseInt(fromDateStr.substring(6, 8));
		return "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&a=" + fromMonth + "&b=" + fromDay + "&c=" + fromYear;
	}
}
