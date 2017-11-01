package com.slyang.test.springtr.demo1;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class AccountServiceImpl implements AccountService {

	// 注入转账到的DAO的类  不是注解要提供set方法
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	// 注入事务管理的模板
	private TransactionTemplate transactionTemplate;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	/**
	 * @param out   ：转出账号
	 * @param in    ：转入账号
	 * @param money :转账金额
	 */
	@Override
	public void transfer(String out, String in, Double money) {
		/*
		accountDao.outMoney(out, money);
		// 出现错误， 只转出不到账，所以需要事务管理器
		int i = 1/0;
		accountDao.inMoney(in, money);
		*/

		// 使用事务管理 
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {

			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				accountDao.outMoney(out, money);
				int i = 1 / 0;
				accountDao.inMoney(in, money);
			}
		});

	}

}
