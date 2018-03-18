package com.slyang.test.springcache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

	
	@Cacheable(value = "memoryCache", key = "'hello:user:'+#userid")// 使用了一个缓存名叫 accountCache
	public String doSomething(String userid) {
		System.out.println("real querying db...");
		return "return  doSomething" + userid;

	}

}