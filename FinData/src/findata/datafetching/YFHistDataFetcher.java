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
	public static final String YH_DATA_SEPERATOR = ",";
	public static final String YH_DATA_DATE_FORMAT = "yyyy-MM-dd";
	public static final SimpleDateFormat YH_DATA_DATE_FORMATTER = new SimpleDateFormat(YH_DATA_DATE_FORMAT);

	public static String getBaseUrl(String symbol)
	{
		return "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&c=1962";
	}

	public static Stream<String> getHistDataStream(String symbol) throws Exception
	{
		Stream<String> str = (new BufferedReader(new InputStreamReader(NetAccessHelper.getInputStreamFromUrl(getBaseUrl(symbol))))).lines();
		return str;
	}

	public static Stream<YFHistData> getSortedHistDataStream(String symbol) throws Exception
	{
		return getHistDataStream(symbol).map(new Function<String, YFHistData>() {
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
}
