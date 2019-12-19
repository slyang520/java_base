package com.slyang.test.springaop.springaop;

import com.slyang.test.springaop.springaop.testAop.UserDao;
import com.slyang.test.springaop.springaop.testAop.child.UserDao2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * http://blog.csdn.net/javazejian/article/details/56267036
 * <p>
 * <p>
 * a.前置通知:在执行目标方法前实施某种逻辑.
 * b.后置通知:在执行目标方法后实施某种逻辑.
 * c.环绕通知在执行目标方法前后实施某种逻辑.
 * d.异常抛出通知:在执行目标方法抛出异常实施某种逻辑.
 * e.引入通知:在执行目标类添加新方法和属性.
 */
public class SpringAop {

	public static void main(String[] args) {

		//加载配置文件
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
		UserDao userDao = (UserDao) applicationContext.getBean("userDaos");
	    int test = userDao.addUser("2222");

//		UserDao2 userDao2 = (UserDao2) applicationContext.getBean("userDaos2");
//		userDao2.addUser();

	}

}
