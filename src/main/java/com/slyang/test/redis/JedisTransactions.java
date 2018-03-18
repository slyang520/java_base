package com.slyang.test.redis;


import redis.clients.jedis.Jedis;

/**
 * 事务
 */
public class JedisTransactions {

	public static void main(String[] arg) {

		Jedis jedis = new Jedis("localhost", 6379);

//		jedis.watch (key1, key2, ...);
//		Transaction t = jedis.multi();
//		t.set("foo", "bar");
//		t.exec();

	}

}
