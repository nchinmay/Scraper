package symbolaccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.io.Util;

import datautil.RunHelper;

public abstract class AbstractSymbolListParser {
	/**
	 * Reference : http://www.nasdaqtrader.com/trader.aspx?id=symboldirdefs
	 */
	public static final String FTP_SERVER = "ftp.nasdaqtrader.com";
	public static final String FTP_DIRECTORY = "/symboldirectory";
	public static final String FTP_USER = "anonymous";
	public static final String FTP_PASSWORD = "anonymous";

	public static final String LINE_DELIM = "\\|";

	public static final int MAX_COMMON_STOCK_SYMBOL_LEN = 5;
	public static final String TEST_ISSUE = "Y";
	public static final String NOT_TEST_ISSUE = "N";

	public abstract String getFileName();

	public abstract String parseLine(String line);

	public Set<String> parseFile() {
		Set<String> symbols = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					RunHelper.getTodaySymbolDataDirectory() + getFileName()));
			symbols = new HashSet<String>();
			String line = br.readLine();
			while (line != null) {
				String symbol = parseLine(line);
				if (symbol != null)
					symbols.add(symbol);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return symbols;
	}

	public boolean getSymbolDataFile() throws IOException {
		FTPClient ftp = new FTPClient();
		// Connect
		ftp.connect(FTP_SERVER);
		showServerReply(ftp);
		int reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			System.out.println(FTP_SERVER + " refused connection.");
			return false;
		}

		// Enter Remote Passive Mode
		ftp.enterRemotePassiveMode();
		showServerReply(ftp);

		// Login
		ftp.login(FTP_USER, FTP_PASSWORD);
		showServerReply(ftp);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.logout();
			ftp.disconnect();
			System.out.println("Failed to login to " + FTP_SERVER);
			return false;
		}

		// CWD
		ftp.changeWorkingDirectory(FTP_DIRECTORY);
		showServerReply(ftp);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.logout();
			ftp.disconnect();
			System.out.println("Failed to change working directory to "
					+ FTP_DIRECTORY);
			return false;
		}

		// Return FileStream
		InputStream input = ftp.retrieveFileStream(getFileName());
		OutputStream output = new FileOutputStream(
				RunHelper.getTodaySymbolDataDirectory() + getFileName());
		showServerReply(ftp);
		Util.copyStream(input, output);
		input.close();
		output.close();
		if (!ftp.completePendingCommand()) {
			ftp.logout();
			ftp.disconnect();
			System.out.println("File transfer failed");
			return false;
		}
		showServerReply(ftp);

		return true;
	}

	/**
	 * HELPERS
	 */
	public static boolean isValidCommonStock(String symbol) {
		return symbol.length() > 0
				&& symbol.length() <= MAX_COMMON_STOCK_SYMBOL_LEN
				&& StringUtils.isAlpha(symbol);
	}

	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}
}
