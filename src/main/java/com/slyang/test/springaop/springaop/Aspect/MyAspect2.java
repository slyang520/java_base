package com.slyang.test.springaop.springaop.Aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;

@Aspect
public class MyAspect2 implements Ordered {

	@Override
	public int getOrder() {
		return 1;
	}

	/**
	 * 使用Pointcut定义切点
	 */
	@Pointcut("execution(* com.slyang.test.springaop.springaop.UserDao.addUser(..))")
	private void myPointcut() {
	}

	@Before("myPointcut()")
	public void beforeOne() {
		System.out.println("前置通知....执行顺序1--AspectTwo");
	}

	@Before("myPointcut()")
	public void beforeTwo() {
		System.out.println("前置通知....执行顺序2--AspectTwo");
	}

	@AfterReturning(value = "myPointcut()")
	public void AfterReturningThree() {
		System.out.println("后置通知....执行顺序3--AspectTwo");
	}

	@AfterReturning(value = "myPointcut()")
	public void AfterReturningFour() {
		System.out.println("后置通知....执行顺序4--AspectTwo");
	}

}
