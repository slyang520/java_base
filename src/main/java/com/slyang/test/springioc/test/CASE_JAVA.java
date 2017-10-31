package com.slyang.test.springioc.test;

import com.slyang.test.springioc.BeanCompent;
import com.slyang.test.springioc.dao.AccountDao;
import com.slyang.test.springioc.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CASE_JAVA {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanCompent.class);

		//名称必须方法名称一致
		AccountService accountService = (AccountService) applicationContext.getBean("accountService");
		AccountDao accountDao = applicationContext.getBean(AccountDao.class);
		AccountService accountService2 = (AccountService) applicationContext.getBean("accountService2");

		accountService.doSomething();


	}

}
