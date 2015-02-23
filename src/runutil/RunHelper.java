package runutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunHelper
{
	public static final String PATH_DELIM = "/";
	public static final String RUN_DATA_DIR_NAME = "rundata";
	public static final String SYMBOL_DATA_DIR = "symboldata";

	public static String getCurrentWorkingDirectory()
	{
		return System.getProperty("user.dir") + PATH_DELIM;
	}

	public static String getTodayRunDataDirectory() throws IOException
	{
		StringBuilder todayDir = new StringBuilder(getCurrentWorkingDirectory()).append(PATH_DELIM).append(RUN_DATA_DIR_NAME).append(PATH_DELIM)
				.append(DateTimeHelper.getTodayDate()).append(PATH_DELIM);
		Path path = Paths.get(todayDir.toString());
		if (!Files.exists(path))
		{
			Files.createDirectories(path);
		}
		return todayDir.toString();
	}

	public static String getTodaySymbolDataDirectory() throws IOException
	{
		StringBuilder todaySymbolDir = new StringBuilder(getCurrentWorkingDirectory()).append(PATH_DELIM).append(RUN_DATA_DIR_NAME).append(PATH_DELIM)
				.append(DateTimeHelper.getTodayDate()).append(PATH_DELIM).append(SYMBOL_DATA_DIR).append(PATH_DELIM);
		Path path = Paths.get(todaySymbolDir.toString());
		if (!Files.exists(path))
		{
			Files.createDirectories(path);
		}
		return todaySymbolDir.toString();
	}
}
