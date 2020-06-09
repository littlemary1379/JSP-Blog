package com.cos.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class YoutubeParser {

	public static String getYoutubePreview(String content) {
		String another = null;
		String youtubeurl = null;
		Document doc = Jsoup.parse(content);
		// System.out.println(doc);
		Elements aTags = doc.select("a");
		// System.out.println(aTags);
	
		//System.out.println(value);

		for (Element element : aTags) {
			
			String value = element.attr("href");

				if (value.contains("https://www.youtube.com/")) {
					System.out.println("aTags: " + aTags);
					System.out.println("value: " + value);
					String[] sp = value.split("=");
					another = sp[1];
					System.out.println("another :" +another);
					youtubeurl = "</br><iframe src='https://www.youtube.com/embed/" + another
							+ "' style='width: 500px; height: 300px'></br>";
					element.after(youtubeurl);

				} else if (value.contains("https://youtu.be/")) {
					String[] sp = value.split("/");
					another = sp[3];
					System.out.println("another :"+another);
					youtubeurl = "</br><iframe src='https://www.youtube.com/embed/" + another
							+ "' style='width: 500px; height: 300px'></br>";
					element.after(youtubeurl);

			}
				
	
		}
			
		// System.out.println(doc);

		return doc.toString();
	}
}
