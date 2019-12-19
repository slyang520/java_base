package com.slyang.test.springaop.springaop.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;

@Aspect
public class MyAspect implements Ordered{

	@Override
	public int getOrder() {
		return 0;
	}

	/**
	 * 前置通知
//	 */
	@Before("execution(* com.slyang.test.springaop.springaop.testAop.UserDao.addUser(..))")
	public void before() {
		System.out.println("前置通知...."+"order: "+getOrder());
		throw new RuntimeException("123");
	}

	@Pointcut("execution(* com.slyang.test.springaop.springaop.testAop.UserDao.addUser(..))")
	private void myPointcut() {
	}

	/**
	 * 后置通知
	 * returnVal,切点方法执行后的返回值
	 */
	@AfterReturning(value = "myPointcut() && args(testp)",
			returning = "returnVal")
	public void AfterReturning(int returnVal,String testp) {
		System.out.println("MyAspect 后置通知...." + returnVal);

	}

	/**
	 * 后置通知
	 * returnVal,切点方法执行后的返回值
	 */
	@AfterReturning(value = "execution(* com.slyang.test.springaop.springaop.testAop.UserDaoImp.testInner(..))",
			returning = "returnVal")
	public void AfterReturningInner(int returnVal) {
		System.out.println("MyAspect 后置通知...." + returnVal);
	}



//	/**
//	 * 环绕通知
//	 *
//	 * @param joinPoint 可用于执行切点的类
//	 * @return
//	 * @throws Throwable
//	 */
//	@Around("execution(* com.slyang.test.springaop.springaop.testAop.UserDao.addUser(..))")
//	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//		System.out.println("环绕通知前....");
//		Object obj = (Object) joinPoint.proceed();
//		System.out.println("环绕通知后....");
//		return obj;
//	}

	/**
	 * 抛出通知
	 *
	 * @param e
	 */
	@AfterThrowing(value = "execution(* com.slyang.test.springaop.springaop.testAop.UserDao.addUser(..))", throwing = "e")
	public void afterThrowable(Throwable e) {
		System.out.println("MyAspect 出现异常:msg=" + e.getMessage());
	}

	/**
	 * 无论什么情况下都会执行的方法
	 */
	@After(value = "execution(* com.slyang.test.springaop.springaop.testAop.UserDao.addUser(..))")
	public void after() {
		System.out.println("MyAspect 最终通知...."+"order: "+getOrder());
	}

}
