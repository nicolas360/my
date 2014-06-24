package com.zhuc.my.jdk.jaxb.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @version 2013-6-24 下午7:51:58
 */
public class JaxbUtils {

	private static final Logger logger = LoggerFactory.getLogger(JaxbUtils.class);

	/**
	 * JavaBean转成xml
	 * @param o
	 * @return
	 */
	public static String convertJavaBeanToXml(Object o) {
		String result = null;
		try {
			JAXBContext ctx = JAXBContext.newInstance(o.getClass());
			Marshaller mashaller = ctx.createMarshaller();
			mashaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
			mashaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter writer = new StringWriter();
			mashaller.marshal(o, writer);
			result = writer.toString();
		} catch (Exception e) {
			logger.error("", e);
		}

		return result;
	}

	/**
	 * xml转成JavaBean
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyXmlToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			logger.error("", e);
		}

		return t;
	}

}
