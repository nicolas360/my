package com.zhuc.my.xstream.t7;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Product product = new Product("Banana", "123", 23.00);
		XStream xstream = new XStream(new JettisonMappedXmlDriver());
		//		XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
		//		XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
		//			@Override
		//			public HierarchicalStreamWriter createWriter(Writer writer) {
		//				return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
		//			}
		//		});
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("product", Product.class);

		System.out.println(xstream.toXML(product));

	}

}
