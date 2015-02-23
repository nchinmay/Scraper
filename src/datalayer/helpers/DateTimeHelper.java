package datalayer.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeHelper
{

	public static int getTodayDate()
	{
		return Integer.parseInt((new SimpleDateFormat("yyyyMMdd")).format(new Date()));
	}
}
