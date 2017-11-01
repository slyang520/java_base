package com.slyang.test.springtr.demo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 转账案例测试类
 *
 * @author 61582
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-tr-1.xml")
public class SpringDemo1 {

	// 测试业务层类：
	@Resource(name = "accountService")
	private AccountService accountService;

	@Test
	public void demo1() {
		accountService.transfer("sly", "sly2", 2d);
	}
}
