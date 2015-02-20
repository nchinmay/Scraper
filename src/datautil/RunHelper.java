package datautil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunHelper
{
	public static final String PATH_DELIM = "\\";

	public static String getCurrentRunDirectory()
	{
		return System.getProperty("user.dir") + PATH_DELIM;
	}

	public static String getTodayDataDirectory() throws IOException
	{
		String todayDir = getCurrentRunDirectory() + PATH_DELIM + "Data" + PATH_DELIM + DateTimeHelper.getTodayDate() + PATH_DELIM;
		Path path = Paths.get(todayDir);
		if (!Files.exists(path))
		{
			Files.createDirectories(path);
		}
		return todayDir;
	}
}
