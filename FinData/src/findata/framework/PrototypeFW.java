package findata.framework;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import datalayer.objects.findata.YFHistData;
import findata.datafetching.YFHistDataFetcher;

/**
 * TEST CLASS FOR FRAMEWORK CONCEPTUALIZATION
 *
 */
public class PrototypeFW
{
	private static final Set<String> testSymbols = new HashSet<String>();

	public static void main(String[] args) throws Exception
	{
		/**
		 * http://money.cnn.com/2015/03/01/investing/stocks-to-buy-march-investing/index.html
		 */
		testSymbols.add("AAL");
		testSymbols.add("DAL");
		testSymbols.add("MU");
		testSymbols.add("YHOO");
		testSymbols.add("AA");
		testSymbols.add("KORS");
		testSymbols.add("TSLA");

		for (String symbol : testSymbols)
		{
			Stream<YFHistData> str = YFHistDataFetcher.getSortedHistDataStream(symbol);
			str.forEach(s -> System.out.println(s));
		}

	}
}
