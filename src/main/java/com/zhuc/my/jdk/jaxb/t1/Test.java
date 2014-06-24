package com.zhuc.my.jdk.jaxb.t1;

import java.util.ArrayList;
import java.util.List;

import com.zhuc.my.jdk.jaxb.utils.JaxbUtils;

/**
 * @author zhuc
 * @version 2013-6-24 下午8:11:56
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Father f = new Father(12, "father-1");
		List<Son> list = new ArrayList<Son>();
		list.add(new Son("我son-1", 20));
		list.add(new Son("我son-2", 21));
		f.setSon(list);

		System.out.println(JaxbUtils.convertJavaBeanToXml(f));
		
		String str = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>"+
			"<FATHER id=\"12\">"+
			 "   <name>father-1</name>"+
			 "   <sons>"+
			 "       <son>"+
			 "           <age_b>20</age_b>"+
			  "          <name_a>我son-1</name_a>"+
			  "      </son>"+
			 "       <son>"+
			 "           <age_b>21</age_b>"+
			 "           <name_a>我son-2</name_a>"+
			 "       </son>"+
			 "   </sons>"+
			"</FATHER>";
		
		Father f2 = JaxbUtils.converyXmlToJavaBean(str, Father.class);
		System.out.println(f2);
	}

}
