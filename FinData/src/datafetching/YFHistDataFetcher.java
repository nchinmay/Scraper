package datafetching;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import datafetching.helpers.NetAccessHelper;

public class YFHistDataFetcher
{
	public static String getBaseUrl(String symbol)
	{
		return "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&c=1962";
	}

	public static void main(String[] args) throws Exception
	{
		Stream<String> str = (new BufferedReader(new InputStreamReader(NetAccessHelper.getInputStreamFromUrl(getBaseUrl("AAPL"))))).lines();
		str.forEach(System.out::println);
	}
}
