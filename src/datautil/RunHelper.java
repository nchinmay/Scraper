package datautil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunHelper
{
	public static final String PATH_DELIM = "\\";
	public static final String RUN_DATA_DIR_NAME = "rundata";

	public static String getCurrentRunDirectory()
	{
		return System.getProperty("user.dir") + PATH_DELIM;
	}

	public static String getTodayRunDataDirectory() throws IOException
	{
		String todayDir = getCurrentRunDirectory() + PATH_DELIM + RUN_DATA_DIR_NAME + PATH_DELIM
				+ DateTimeHelper.getTodayDate() + PATH_DELIM;
		Path path = Paths.get(todayDir);
		if (!Files.exists(path))
		{
			Files.createDirectories(path);
		}
		return todayDir;
	}
}
