package com.zhuc.my.aspectj.t2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspect2 {

	private static final Logger logger = LoggerFactory.getLogger(Aspect2.class);

	/**
	 * 单纯定义一个切入点
	 */
	@Pointcut(value = "execution(* com.zhuc.my.aspectj.t2..*(..))")
	private void pc() {
	}

	/**
	 * @param joinPoint
	 */
	//	@Before(value = "execution(* com.zhuc.my.aspectj.t2..*(..))") //同时定义切面和切入点
	@Before(value = "pc()")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.debug("Before: " + joinPoint.getSignature().getName());
	}

	//	@Around(value = "execution(* com.zhuc.my.aspectj.t2..*(..))")
	@Around(value = "pc()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("Around: " + joinPoint.getSignature().getName());
		logger.debug("Before");
		Object obj = joinPoint.proceed();
		logger.debug("End");

		return obj;
	}

}
