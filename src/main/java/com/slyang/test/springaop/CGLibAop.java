package com.slyang.test.springaop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by slyang on 17/6/20.
 */
public class CGLibAop {

	public static void main(String[] args) {

		CGLib proxy = new CGLib();
		RealSubject t = (RealSubject) proxy.getProxy(RealSubject.class);

		t.doSome();

	}

	class RealSubject {

		public void doSome() {
			System.out.println("RealSubject  doSome");
		}

	}

	static class CGLib implements MethodInterceptor {

		private Enhancer enhancer = new Enhancer();

		// 动态生成子类
		public Object getProxy(Class clazz) {
			enhancer.setSuperclass(clazz);//目标对象类
			enhancer.setCallback(this);
			return enhancer.create();//通过字节码技术创建目标对象类的子类实例作为代理
		}

		@Override
		public Object intercept(Object o,
								Method method,
								Object[] objects,
								MethodProxy methodProxy) throws Throwable {

			System.out.println("------------------before------------------");

			Object obj = methodProxy.invokeSuper(o, objects);

			System.out.println("-------------------after------------------");

			return null;
		}

	}


}
