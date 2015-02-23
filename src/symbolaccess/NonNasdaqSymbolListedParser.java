package symbolaccess;

import java.io.IOException;

public class NonNasdaqSymbolListedParser extends AbstractSymbolListParser {
	public static final String FILE_NAME = "otherlisted.txt";

	@Override
	public String getFileName() {
		return FILE_NAME;
	}

	public static void main(String[] args) throws IOException {
		NonNasdaqSymbolListedParser nnsp = new NonNasdaqSymbolListedParser();
		nnsp.getSymbolDataFile();
	}
}
