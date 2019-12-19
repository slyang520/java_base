package com.slyang.test.springaop.springaop.Aspect;

import org.aspectj.lang.annotation.*;
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
	@Pointcut("execution(* com.slyang.test.springaop.springaop.testAop.UserDao.addUser(..))")
	private void myPointcut() {
	}

	@Before("myPointcut()")
	public void beforeOne() {
		System.out.println("MyAspect2 前置通知....执行顺序1--AspectTwo"+"order: "+getOrder());
	}

	@Before("myPointcut()")
	public void beforeTwo() {
		System.out.println("MyAspect2 前置通知....执行顺序2--AspectTwo");
	}

	@AfterReturning(value = "myPointcut()")
	public void AfterReturningThree() {
		System.out.println("后置通知....执行顺序3--AspectTwo");
	}

	@AfterReturning(value = "myPointcut()")
	public void AfterReturningFour() {
		System.out.println("MyAspect2 后置通知....执行顺序4--AspectTwo");
	}

	/**
	 * 无论什么情况下都会执行的方法
	 */
	@After(value = "myPointcut()")
	public void after() {
		System.out.println("MyAspect2 最终通知...."+"order: "+getOrder());
		//throw new RuntimeException("123");
	}
}
