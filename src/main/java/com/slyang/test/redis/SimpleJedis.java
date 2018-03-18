package com.slyang.test.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;


//	set key ''
//		get key
//		del key
//
//		incr key   //自增
//		expire key time //设置过期时间
//		ttl key  //查看过期时间  -2不存在  -1永不会过期  >0剩余过期时间s
//
//	list
//		RPUSH, LPUSH, LLEN, LRANGE, LPOP, RPOP.
//
//		lrange key 0 -1//取出list的 所有
//		lrange key 0 0 //取出list的 第0个
//		lrange key 0 1//取出list的  第0个>>第一个
//
//		llen key  //取出list的数量
//		lpop key  //移除list的第一个
//		rpop key  //移除list的最后一个
//
// set
//		sadd key ''  //添加
//		srem key ''  //移除
//		sismember key '' //测试一个set被添加过 1>加过 0>没有
//		smembers key   //返回一个set集合
//		sunion    //取2个 或2个 key 以上的 并集 返回list
//
//	sorted sets (z-set)
//
// 		zadd key sorce ''  //添加按sorce排序
//		zrange key 0 -1//取出list的所以
//
//	hash
//		hset user:1000 name "slyang"
//		hset user:1000 email "slyang@flyme.cn"  //存储对象
//
//		hgetall user:1000  //得到对象信息
//		hget user:1000 name //得到对象的属性信息
//
//		hmset user:1001 name "slyang-2" email "slyang@fdsf.cn" //一次存储对象信息
//
//// 	pub/sub
//		PSUBSCRIBE
//		PUBLISH
//		PUBSUB
//		PUNSUBSCRIBE
//		SUBSCRIBE
//		UNSUBSCRIBE
//

public class SimpleJedis {

	public static void main(String[] arg) {

		Jedis jedis = new Jedis("localhost", 6379);
		String value = jedis.get("foo");
		String value2 = jedis.get("testaa");

		//KEY NX 不存在才去设置
		String code = jedis.set("test", "testValue", "NX", "EX", 30);

		System.out.println(String.format("[value =%1s,  value2=%2s]", value, value2));
		System.out.println(String.format("[code =%1s]", code));

		// hash
		Map<String, String> jMap = new HashMap<>();
		jMap.put("name", "slyang");
		jMap.put("email", "slyang520@yeah.net");
		String hmset = jedis.hmset("user:1003", jMap);


		Map<String, String> hgetMap = jedis.hgetAll("user:1002");
		Map<String, String> hgetMap2 = jedis.hgetAll("user:1003");
		String hgetString = jedis.hget("user:1003", "name");
		String hgetString2 = jedis.hget("user:1003", "name_no");

		System.out.println(String.format("[hmset =%1s  ]", hmset));
		System.out.println(String.format("[hgetMap =%1s,  hgetMap2=%2s]", hgetMap, hgetMap2));
		System.out.println(String.format("[hgetString =%1s,  hgetString2=%2s]", hgetString, hgetString2));

		// set
	}

}
