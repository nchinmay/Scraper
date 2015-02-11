package datautil;

public class RunHelper {
	public static String getCurrentRunDirectory() {
		return System.getProperty("user.dir");
	}

	public static String getTodayDataDirectory() {
		return getCurrentRunDirectory() + "\\Data\\"
				+ DateTimeHelper.getTodayDate() +"\\";
	}
}
