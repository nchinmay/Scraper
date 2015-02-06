package scraper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraper {
	private Document doc;

	public Scraper(String baseUri) {
		try {
			doc = Jsoup.connect(baseUri).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return doc == null ? null : doc.title();
	}

	public static void main(String[] args) {
		Scraper scr = new Scraper("http://www.google.com/");
		System.out.println(scr.getTitle());
	}
}
