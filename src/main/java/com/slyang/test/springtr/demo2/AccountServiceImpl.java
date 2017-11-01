package com.slyang.test.springtr.demo2;

public class AccountServiceImpl implements AccountService {

	// 注入转账到的DAO的类  不是注解要提供set方法
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	/**
	 * @param out    ：转出账号
	 * @param in	   ：转入账号
	 * @param money  :转账金额
	 */
	@Override
	public void transfer(String out, String in, Double money) {
		accountDao.outMoney(out, money);
		int i = 1/0;
		accountDao.inMoney(in, money);
	}

}
