package com.slyang.test.springioc;

import com.slyang.test.springioc.dao.AccountDao;
import com.slyang.test.springioc.dao.AccountDaoImpl;
import com.slyang.test.springioc.service.AccountService;
import com.slyang.test.springioc.service.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanCompent {

	@Bean
	public AccountDao accountDao() {
		return new AccountDaoImpl();
	}

	@Bean
	public AccountService accountService() {
		AccountServiceImpl bean = new AccountServiceImpl();
		//注入dao
		bean.setAccountDao(accountDao());
		return bean;
	}

	/**
	 * accountDao 为依赖的对象
	 */
	@Bean
	public AccountService accountService2(AccountDao accountDao) {
		AccountServiceImpl bean = new AccountServiceImpl();
		bean.setAccountDao(accountDao);
		return bean;
	}

}