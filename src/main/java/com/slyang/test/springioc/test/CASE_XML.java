package com.slyang.test.springioc.test;

import com.slyang.test.springioc.service.AccountService;
import com.slyang.test.springioc.service.MyDBConf;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CASE_XML {

	public static void main(String[] args) {

		//加载配置文件
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc.xml");

		//AccountService accountService1 = (AccountService) applicationContext.getBean("accountService");
		AccountService accountService1 = applicationContext.getBean("accountService", AccountService.class);

		AccountService accountService2 = (AccountService) applicationContext.getBean("accountService2");

		MyDBConf dbConf = (MyDBConf) applicationContext.getBean("dbConf");
		System.out.println(dbConf.toString());


	}

}
