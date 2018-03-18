package com.slyang.test.springcache.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-cache.xml")
public class Test {

	@Autowired
	AccountService accountService;

	Cache cache;

	@Autowired
	SimpleCacheManager simpleCacheManager;

	@org.junit.Test
	public void myCaseTest() {

		System.out.println("start ...");

		System.out.println(accountService.doSomething("123"));
		System.out.println(accountService.doSomething("123"));

		System.out.println(accountService.doSomething("456"));

		System.out.println("end ...");

	}


}
