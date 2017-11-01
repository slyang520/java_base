package com.slyang.test.springtr.demo4;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * propagation	：事务的传播行为
 * isolation	：事务的隔离级别
 * readOnly		：只读
 * rollbackFor  ：发生哪些异常回滚
 * noRollbackFor：发生哪些异常不回滚
 */
@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	/**
	 * @param out   ：转出账号
	 * @param in    ：转入账号
	 * @param money :转账金额
	 */
	@Override
	public void transfer(String out, String in, Double money) {
		accountDao.outMoney(out, money);
		int i = 1 / 0;
		accountDao.inMoney(in, money);
	}

}
