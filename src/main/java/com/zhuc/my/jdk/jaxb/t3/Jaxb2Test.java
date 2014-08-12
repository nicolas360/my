package com.zhuc.my.jdk.jaxb.t3;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author zhuc
 * @version 2012-11-20 上午9:44:15
 */
public class Jaxb2Test {
	private JAXBContext context = null;
	private StringWriter writer = null;
	private StringReader reader = null;
	private AccountBean bean = null;

	@Before
	public void init() {
		bean = new AccountBean();
		bean.setAddress("北京");
		bean.setVersion("2.0");
		bean.setEmail("email");
		bean.setId(1);
		bean.setName("jack");
		Birthday day = new Birthday();
		day.setBirthday("2010-11-22");
		bean.setBirthday(day);
		try {
			context = JAXBContext.newInstance(AccountBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void destory() {
		context = null;
		bean = null;
		try {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
			if (reader != null) {
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.gc();
	}

	public void out(Object o) {
		System.out.println(o);
	}

	public void failRed(Object o) {
		System.err.println(o);
	}

	@Test
	public void testBean2XML() {
		try {

			// 下面代码演示将对象转变为xml
			Marshaller mar = context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_ENCODING, "utf-8");// 编码格式
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
			mar.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xml头信息
			writer = new StringWriter();
			mar.marshal(bean, writer);
			out(writer);

			// 下面代码演示将上面生成的xml转换为对象
			reader = new StringReader(writer.toString());
			Unmarshaller unmar = context.createUnmarshaller();
			bean = (AccountBean) unmar.unmarshal(reader);
			out(bean);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testList2XML() {
		ListBean listBean = new ListBean();
		listBean.setName("list to xml");
		List<Object> list = new ArrayList<Object>();
		list.add(bean);
		bean = new AccountBean();
		bean.setAddress("china");
		bean.setEmail("tom@125.com");
		bean.setId(2);
		bean.setName("tom");
		Birthday day = new Birthday("2010-11-22");
		bean.setBirthday(day);
		Account acc = new Account();
		acc.setAddress("china");
		acc.setEmail("tom@125.com");
		acc.setId(2);
		acc.setName("tom");
		day = new Birthday("2010-11-22");
		acc.setBirthday(day);
		list.add(bean);
		list.add(acc);
		listBean.setList(list);
		try {
			context = JAXBContext.newInstance(ListBean.class);
			// 下面代码演示将对象转变为xml
			Marshaller mar = context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串

			writer = new StringWriter();
			mar.marshal(listBean, writer);
			out(writer);
			// 下面代码演示将上面生成的xml转换为对象
			reader = new StringReader(writer.toString());
			Unmarshaller unmar = context.createUnmarshaller();
			listBean = (ListBean) unmar.unmarshal(reader);
			out(listBean.getList().get(0));
			out(listBean.getList().get(1));
			out(listBean.getList().get(2));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMap2XML() {
		MapBean mapBean = new MapBean();
		Map<String, AccountBean> map = new HashMap<String, AccountBean>();
		map.put("NO1", bean);
		bean = new AccountBean();
		bean.setAddress("china");
		bean.setEmail("tom@125.com");
		bean.setId(2);
		bean.setName("tom");
		Birthday day = new Birthday("2010-11-22");
		bean.setBirthday(day);
		map.put("NO2", bean);
		mapBean.setMap(map);
		try {
			context = JAXBContext.newInstance(MapBean.class);
			// 下面代码演示将对象转变为xml
			Marshaller mar = context.createMarshaller();
			mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
			writer = new StringWriter();
			mar.marshal(mapBean, writer);
			out(writer);

			// 下面代码演示将上面生成的xml转换为对象
			reader = new StringReader(writer.toString());
			Unmarshaller unmar = context.createUnmarshaller();
			mapBean = (MapBean) unmar.unmarshal(reader);
			out(mapBean.getMap());
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
}