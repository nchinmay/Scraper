package findata.framework;

import java.util.stream.Stream;

import findata.datafetching.YFHistDataFetcher;

/**
 * TEST CLASS FOR FRAMEWORK CONCEPTUALIZATION
 *
 */
public class PrototypeFW
{
	public static void main(String[] args) throws Exception
	{
		Stream<String> str = YFHistDataFetcher.getHistDataStream("GOOGL");
		str.forEach(s -> printAsCsv(s));
	}

	private static void printAsCsv(String s)
	{
		String[] part = s.split(",");
		String print = "Date - " + part[0] + " Open - " + part[1] + " High - " + part[2] + " Low - " + part[3] + " Close - " + part[4] + " Volume - " + part[5] + " Adj. Close - "
				+ part[6];
		System.out.println(print);
	}
}
