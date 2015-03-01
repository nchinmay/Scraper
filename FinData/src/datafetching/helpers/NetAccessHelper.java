package datafetching.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetAccessHelper
{
	public static InputStream getInputStreamFromUrl(String urlString) throws Exception
	{
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if (conn.getResponseCode() != 200) throw new IOException(conn.getResponseMessage());
		return conn.getInputStream();
	}
}
