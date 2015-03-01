package findata.datafetching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import findata.helpers.NetAccessHelper;

public class YFHistDataFetcher
{
	public static String getBaseUrl(String symbol)
	{
		return "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&c=1962";
	}

	public static Stream<String> getHistDataStream(String symbol) throws Exception
	{
		Stream<String> str = (new BufferedReader(new InputStreamReader(NetAccessHelper.getInputStreamFromUrl(getBaseUrl(symbol))))).lines();
		return str;
	}
}
