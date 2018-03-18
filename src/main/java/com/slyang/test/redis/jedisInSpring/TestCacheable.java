package com.slyang.test.redis.jedisInSpring;

import org.springframework.cache.annotation.Cacheable;

public class TestCacheable {

	@Cacheable("cache1")//Cache是发生在cache1上的
	public String find() {
		return "this is cacheable test";
	}

	@Cacheable({"cache1", "cache2"})//Cache是发生在cache1和cache2上的
	public String find2() {
		return "this is cacheable test";
	}


}
