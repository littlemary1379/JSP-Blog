package com.cos.blog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class YoutubeParser {
	
	public void getYoutubePreview() {
		//String content="<p><a href=\"https://www.youtube.com/watch?v=zCIr-lbCuXc&amp\">https://www.youtube.com/watch?v=zCIr-lbCuXc</a><br></p>";
		String content="<p><a href=\"https://youtu.be/zCIr-lbCuXc?list=PLLoamEZ5bJuHvVYx5w0ayFeC93apFEQml\" target=\"_blank\" style=\"background-color: rgb(255, 255, 255); font-size: 1rem;\">https://youtu.be/zCIr-lbCuXc?list=PLLoamEZ5bJuHvVYx5w0ayFeC93apFEQml</a><br></p><p><br></p>\r\n";
		Document doc = Jsoup.parse(content);
		//System.out.println(doc);
		Elements aTags=doc.select("a");
		//System.out.println(aTags);
		String value=aTags.text();
		System.out.println(value);
		if(value.contains("https://www.youtube.com/")||value.contains("https://youtu.be/")) {
			System.out.println("유튜브");
			if(value.contains("https://www.youtube.com/")) {
				System.out.println("으아악");
				String[] another=value.split("/");
				System.out.println("https://www.youtube.com/"+another[2]);
				
				
			}else {
				String[] another=value.split("/");
				System.out.println("https://youtu.be/"+another[3]);
			}
		}else {
			System.out.println("틀렷어");
		}
	}
}
