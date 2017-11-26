package com.slyang.test.springtr.demo4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Spring 声明式事务管理的方式2的测试: 基于注解的方式的配置
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-tr-4.xml")
public class SpringDemo4 {

	@Resource(name = "accountService")
	private AccountService accountService;

	@Test
	public void demo1() {
		accountService.transfer("sly", "sly2", 20d);
	}

}
