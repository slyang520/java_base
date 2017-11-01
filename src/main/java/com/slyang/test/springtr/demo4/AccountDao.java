package com.slyang.test.springtr.demo4;

public interface AccountDao {
	/**
	 * @param out   ：转出账号
	 * @param money ：转账金额
	 */
	void outMoney(String out, Double money);

	/**
	 * @param in    ：转入账号
	 * @param money ：转账金额
	 */
	void inMoney(String in, Double money);
}
