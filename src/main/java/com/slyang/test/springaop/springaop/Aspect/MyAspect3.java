package com.slyang.test.springaop.springaop.Aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
                                                                                               
@Aspect
public class MyAspect3 implements Ordered {

	@Override
	public int getOrder() {
		return 1;
	}

	/**
	 * 匹配包下的及其子包的所有方法
	 * <p>
	 * 使用Pointcut定义切点
	 */
	@Pointcut("within(com.slyang.test.springaop.springaop.testAop..*)")
	private void myPointcut() {
	}

	@Before("myPointcut()")
	public void beforeOne() {
		System.out.println("前置通知....执行顺序1--MyAspect3");
	}

	@Before("myPointcut()")
	public void beforeTwo() {
		System.out.println("前置通知....执行顺序2--MyAspect3");
	}

	@AfterReturning(value = "myPointcut()")
	public void AfterReturningThree() {
		System.out.println("后置通知....执行顺序3--MyAspect3");
	}

	@AfterReturning(value = "myPointcut()")
	public void AfterReturningFour() {
		System.out.println("后置通知....执行顺序4--MyAspect3");
	}

}
