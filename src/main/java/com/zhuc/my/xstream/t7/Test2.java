package com.zhuc.my.xstream.t7;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String json = "{\"product\":{\"name\":\"Banana\",\"id\":123" + ",\"price\":23.0}}";

		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		xstream.alias("product", Product.class);
		Product product = (Product) xstream.fromXML(json);
		System.out.println(product);

	}

}
