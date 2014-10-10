package com.zhuc.my.aspectj.t1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

	/**
	 * 模块
	 * @return
	 */
	String module() default "";

	/**
	 * 功能
	 * @return
	 */
	String function() default "";
}
