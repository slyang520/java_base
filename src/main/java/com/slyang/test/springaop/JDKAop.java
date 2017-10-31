package com.slyang.test.springaop;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 依赖与接口
 * Created by slyang on 17/6/20.
 */
public class JDKAop {


	public static void main(String[] arg) {

		RealSubject target = new RealSubject();
		MyInvoke invoke = new MyInvoke(target);

		//只能是接口的申明
		Subject proxySubject = (Subject) invoke.getProxy();
		proxySubject.doSome();

	}

	interface Subject {
		void doSome();
	}

	static class RealSubject implements Subject {

		@Override
		public void doSome() {
			System.out.println("RealSubject doSome");
		}

	}

	static class MyInvoke implements InvocationHandler {

		// 目标对象
		private Object target;

		public MyInvoke(Object target) {
			this.target = target;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			System.out.println("------------------before------------------");

			Object obj = method.invoke(target, args);

			System.out.println("-------------------after------------------");

			return obj;
		}

		public Object getProxy() {
			return Proxy.newProxyInstance(target.getClass().getClassLoader(),
					target.getClass().getInterfaces(), this);
		}

	}

}
