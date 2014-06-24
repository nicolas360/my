package com.zhuc.my.dozer;

import org.dozer.CustomConverter;

/**
 * 自定义转换类
 * @author zhuc
 * @create 2013-3-28 下午2:49:39
 */
public class MyCustomConverter implements CustomConverter {

	@Override
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		Object obj = null;
		if (null != sourceFieldValue) {
			if (sourceFieldValue instanceof Integer) {
				if (((Integer) sourceFieldValue).intValue() == 1900) {
					obj = "20世纪";
				} else if (((Integer) sourceFieldValue).intValue() == 2000) {
					obj = "21世纪";
				}
			} else if (sourceFieldValue instanceof String) {
				if ("20世纪".equals(sourceFieldValue)) {
					obj = 1900;
				} else if ("21世纪".equals(sourceFieldValue)) {
					obj = 2000;
				}
			}
		}
		return obj;
	}

}
