package com.zhuc.my.dozer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;

/**
 * @author zhuc
 * @create 2013-3-28 下午3:15:49
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> mappers = new ArrayList<String>();
		mappers.add("com/zhuc/my/dozer/mapper.xml");

		DozerBeanMapper dozer = new DozerBeanMapper();
		dozer.setMappingFiles(mappers);

		Book book = new Book();
		book.setId(100); // id	2个JavaBean一致
		book.setName("书籍名称"); // Book:name(String)  <->  BookVo:nameVo(String)	类型一致,名称不一致
		book.setAuthor("James"); // Book:author(String)  <->  BookVo:authorVo(String)	类型一致,名称不一致
		book.setCapacity("123"); // Book:capacity(String)  <->  BookVo:capacity(Integer)	类型不一致,名称一致
		book.setBirthday(new Date()); // Book:birthday(Date)  <->  BookVo:day(String)	类型名称都不一致,且Date<->String互转
		book.setBookTypeName("科教类"); // Book:bookTypeName(String)  <->  BookVo:BookType:name(String)	源类的字段映射为目标类的复杂对象的字段。
		book.setSource(1900); // Book:source(Integer)  <->  BookVo:source(String)	自定义的转换,需要实现dozer的CustomConverter接口

		BookVo vo = new BookVo();
		dozer.map(book, vo);

		System.out.println(book);
		System.out.println(vo);
	}

}
