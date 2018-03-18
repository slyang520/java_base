package com.slyang.test.redis.jedisInSpring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-redis.xml")
public class JedisInSpring {

//	@Cacheable
//	@Cacheable可以标记在一个方法上，
// 也可以标记在一个类上。
// 当标记在一个方法上时表示该方法是支持缓存的，
// 当标记在一个类上时则表示该类所有的方法都是支持缓存的。
// 对于一个支持缓存的方法，Spring会在其被调用后将其返回值缓存起来，
// 以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，
// 而不需要再次执行该方法。
// Spring在缓存方法的返回值时是以键值对进行缓存的，值就是方法的返回结果，
// 至于键的话，Spring又支持两种策略，默认策略和自定义策略，这个稍后会进行说明。
// 需要注意的是当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的。
// @Cacheable可以指定三个属性，value、key和condition。


//	@CachePut
//	@CacheEvict
//	@Caching
//	@CacheConfig

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory
				= new JedisConnectionFactory();
		jedisConFactory.setHostName("localhost");
		jedisConFactory.setPort(6379);
		return jedisConFactory;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Test
	public void demo1() {
		redisTemplate.opsForSet().add("test_spring_data", "2sfasdfsf");
	}

//	public static void main(String[] arg) {
//		RedisTemplate redisTemplate= new RedisTemplate();
//		SetOperations setOperations= redisTemplate.opsForSet();
//		setOperations.add("ttt",2223,"ffffff");
//		HashOperations hashOperations=redisTemplate.opsForHash();
//		hashOperations.increment()
//	}

}
