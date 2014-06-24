package com.zhuc.my.xstream.t1;

import com.thoughtworks.xstream.XStream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XStream xstream = new XStream();
		//		XStream xstream = new XStream(new DomDriver()); // does not require XPP3 library
		//		XStream xstream = new XStream(new StaxDriver()); // does not require XPP3 library starting with Java 6

		xstream.alias("person", Person.class);
		xstream.alias("phonenumber", PhoneNumber.class);

		Person joe = new Person("Joe", "Walnes");
		joe.setPhone(new PhoneNumber(123, "1234-456"));
		joe.setFax(new PhoneNumber(123, "9999-999"));
		String xml = xstream.toXML(joe);
		System.out.println(xml);
		
		String xml2 = "<person>"+
		  "<firstname>Joe</firstname>"+
		  "<lastname>Walnes</lastname>"+
		  "<phone>"+
		  "  <code>123</code>"+
		   " <number>1234-456</number>"+
		  "</phone>"+
		  "<fax>"+
		   " <code>123</code>"+
		   " <number>9999-999</number>"+
		  "</fax>"+
		"</person>";
		Person newJoe = (Person)xstream.fromXML(xml2);
		System.out.println(newJoe);
	}

}
