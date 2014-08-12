package com.zhuc.my.jdk.jaxb.t2;

import com.zhuc.my.jdk.jaxb.utils.JaxbUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Student student = new Student();
		student.setId(12);
		student.setName("test");

		Role role = new Role();
		role.setDesc("管理");
		role.setName("班长");

		student.setRole(role);

		System.out.println(JaxbUtils.convertJavaBeanToXml(student));

		
		String str = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>"+
			"<student id=\"12\">"+
			"    <role>"+
			 "       <name>班长</name>"+
			  "      <desc>管理</desc>"+
			   " </role>"+
			   " <name>test</name>"+
			"</student>";
		Student s2 = JaxbUtils.converyXmlToJavaBean(str, Student.class);
		System.out.println(s2);
	}

}
