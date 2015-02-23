package symbolaccess;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

public class NonNasdaqSymbolListedParser extends AbstractSymbolListParser {
	public static final String FILE_NAME = "otherlisted.txt";

	public static final int ACT_SYMBOL_INDEX = 0;
	public static final int SECURITY_NAME_INDEX = 1;
	public static final int EXCHANGE_INDEX = 2;
	public static final int CQS_SYMBOL_INDEX = 3;
	public static final int ETF_INDEX = 4;
	public static final int ROUND_LOT_SIZE_INDEX = 5;
	public static final int TEST_ISSUE_INDEX = 6;
	public static final int NASDAQ_SYMBOL_INDEX = 7;

	@Override
	public String getFileName() {
		return FILE_NAME;
	}

	public String parseLine(String line) {
		if (!StringUtils.isEmpty(line)) {
			String[] parts = line.split(AbstractSymbolListParser.LINE_DELIM);
			if (parts.length >= NASDAQ_SYMBOL_INDEX) {
				String symbol = parts[ACT_SYMBOL_INDEX];

				boolean isNotTestIssue = NOT_TEST_ISSUE
						.equals(parts[TEST_ISSUE_INDEX]);
				if (isValidCommonStock(symbol) && isNotTestIssue) {
					// TODO - Do MOAR Stuff With Symbol
					return symbol;
				}
			}
		}
		return null;
	}

	/**
	 * TEST STUFF
	 */
	public static void main(String[] args) throws IOException {
		NonNasdaqSymbolListedParser nnsp = new NonNasdaqSymbolListedParser();
		nnsp.getSymbolDataFile();
		for (String s : nnsp.parseFile())
			System.out.println(s);
		System.out.println(nnsp.parseFile().size());
	}
}
