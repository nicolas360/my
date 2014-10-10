package com.zhuc.my.aspectj.t1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspect1 {

	private static final Logger logger = LoggerFactory.getLogger(Aspect1.class);

	/** 
	 * @param jp 
	 * @param log 
	 */
	@AfterReturning(value = "@annotation(log)", returning = "obj")
	public void doAfterReturning(JoinPoint jp, Log log, Object obj) {
		logger.debug("doAfterReturning......");

		for (Object args : jp.getArgs()) {
			logger.debug("类型: {}, 参数: {}", new Object[] { args.getClass(), args });
		}

		logger.debug(log.module() + "," + log.function());
		logger.debug("returning: " + obj);
	}
}
