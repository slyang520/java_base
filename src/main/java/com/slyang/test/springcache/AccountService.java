package com.slyang.test.springcache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.config.CacheNamespaceHandler;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.stereotype.Component;

@Component
public class AccountService {

//	CacheInterceptor cacheInterceptor;
//	CacheNames、paceHandler cacheNamespaceHandler;
	@Cacheable(value = "memoryCache", key = "'hello:user:'+#userid")// 使用了一个缓存名叫 accountCache
	public String doSomething(String userid) {
		System.out.println("real querying db...");
		return "return  doSomething" + userid;
	}

}
