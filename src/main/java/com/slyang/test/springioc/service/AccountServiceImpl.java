package com.slyang.test.springioc.service;

import com.slyang.test.springioc.dao.AccountDao;

public class AccountServiceImpl implements AccountService {


	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void doSomething() {
		System.out.println("AccountServiceImpl#doSomething......");
		accountDao.addAccount();
	}
}