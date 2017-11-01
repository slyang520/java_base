package com.slyang.test.springtr.demo2;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring 声明式事务管理的方式一的测试
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-tr-2.xml")
public class SpringDemo2 {
	
	/**
	 * 注入代理类：因为代理类进行增强操作 @Resource(name="accountService") 还会丢失money
	 */
	@Resource(name="accountServiceProxy")
	private AccountService accountService;
	
	
	@Test
	public void demo1(){
		accountService.transfer("sly", "sly2", 2d);
	}

}
