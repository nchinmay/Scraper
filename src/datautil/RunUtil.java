package datautil;

public class RunUtil {
	public static String getCurrentRunDirectory() {
		return System.getProperty("user.dir");
	}

	public static String getDataDirectory() {
		return getCurrentRunDirectory() + "\\Data";
	}
}
