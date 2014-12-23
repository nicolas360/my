package com.zhuc.my.dozer.t1;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.alibaba.fastjson.JSON;

/**
 * @version		2014-10-16 上午10:43:04
 * @author		zhuc
 */
public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 该方式用于数据类型为基本类型，名称相同的对象映射
		Mapper mapper = new DozerBeanMapper();
		My m = new My();
		m.setName("zhuc");
		m.setAge(11);

		You y = mapper.map(m, You.class);
		System.out.println("y:" + JSON.toJSONString(y));

		//  or

		You y2 = new You();
		mapper.map(m, y2);
		System.out.println("y2:" + JSON.toJSONString(y2));
	}

}
