package com.zhuc.my.jsoup;

import java.io.File;
import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

public class Test1 {

	@Test
	public void execute() {
		try {
			Response response = Jsoup.connect("http://www.baidu.com/").execute();
			System.out.println(response.cookies());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	@Test
	public void parseString() {
		String html = "<html><head><title>blog</title></head><body onload='test()'><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		System.out.println(doc);

		Elements es = doc.body().getAllElements();
		System.out.println(es.attr("onload"));
		System.out.println(es.select("p"));
	}

	//	@Test
	public void parseUrl() {
		try {
			Document doc = Jsoup.connect("http://www.baidu.com/").get();
			Elements hrefs = doc.select("a[href]");
			System.out.println(hrefs);
			System.out.println("------------------");
			System.out.println(hrefs.select("[href^=http]"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	@Test
	public void parseFile() {
		try {
			File input = new File("input.html");
			Document doc = Jsoup.parse(input, "UTF-8");
			// 提取出所有的编号
			Elements codes = doc.body().select("td[title^=IA] > a[href^=javascript:view]");
			System.out.println(codes);
			System.out.println("------------------");
			System.out.println(codes.html());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}