package com.slyang.test.springioc.service;

import com.slyang.test.springioc.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl2 implements AccountService {


	/**
	 * Autowired 根据类型注入 如果该对象的实例有多个
	 *
	 * @Autowired
	 * @Qualifier("userDao1") 配合使用
	 * <p>
	 * 或者
	 * @Resource(name=“userDao1”)
	 */
	@Autowired
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void doSomething() {
		System.out.println("AccountServiceImpl222222#doSomething......");
		accountDao.addAccount();
	}
}