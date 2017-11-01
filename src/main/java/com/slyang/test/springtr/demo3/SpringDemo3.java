package com.slyang.test.springtr.demo3;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Spring 声明式事务管理的方式2的测试: 基于AspectJ的xml方式的配置
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-tr-3.xml")
public class SpringDemo3 {
	
	@Resource(name="accountService")
	private AccountService accountService;

	@Test
	public void demo1(){
		accountService.transfer("sly", "sly2", 2d);
	}
	
}
