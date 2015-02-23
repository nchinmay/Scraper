package symbolaccess;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class NasdaqSymbolListParser extends AbstractSymbolListParser {
	public static final String FILE_NAME = "nasdaqlisted.txt";

	public static final int SYMBOL_INDEX = 0;
	public static final int SECURITY_NAME_INDEX = 1;
	public static final int MARKET_CATEGORY_INDEX = 2;
	public static final int TEST_ISSUE_INDEX = 3;
	public static final int FINANCIAL_STATUS_INDEX = 4;
	public static final int LOT_SIZE_INDEX = 5;

	@Override
	public String getFileName() {
		return FILE_NAME;
	}

	public void parseFile() {

	}

	public static void parseLine(String line) {
		if (!StringUtils.isEmpty(line)) {
			String[] parts = line.split(AbstractSymbolListParser.LINE_DELIM);
			if (parts.length >= LOT_SIZE_INDEX) {
				String symbol = parts[SYMBOL_INDEX];
				boolean isValidSymbol = symbol.length() > 0
						&& symbol.length() <= MAX_SYMBOL_LEN
						&& StringUtils.isAlpha(symbol.subSequence(0, 1));
				boolean isNotTestIssue = NOT_TEST_ISSUE
						.equals(parts[TEST_ISSUE_INDEX]);
				if (isValidSymbol && isNotTestIssue) {
					// TODO - Do Stuff WIth Symbol
					for (String s : parts)
						System.out.println(s);
				}
			}
		}
	}

	public/**
			 * TEST STUFF
			 */
	static String getTestLine() {
		return "AAL|American Airlines Group, Inc. - Common Stock|Q|N|N|100";
	}

	public static void main(String[] args) throws IOException {
		NasdaqSymbolListParser nsp = new NasdaqSymbolListParser();
		nsp.getSymbolDataFile();
	}

}
