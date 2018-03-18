package com.slyang.test.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class JedisPublishSubscribe {

	public static void main(String[] arg) {

		Jedis jedis = new Jedis("localhost", 6379);

		// PUBLISH hello_chat "Redis is a great caching technique"
		// in redis-cli

		MyListener l = new MyListener();
		jedis.subscribe(l, "hello_chat");


	}

	static class MyListener extends JedisPubSub {
		public void onMessage(String channel, String message) {
			System.out.println(String.format("[  channel = %1s ; message = %2s ]", channel, message));
		}

		public void onSubscribe(String channel, int subscribedChannels) {
		}

		public void onUnsubscribe(String channel, int subscribedChannels) {
		}

		public void onPSubscribe(String pattern, int subscribedChannels) {
		}

		public void onPUnsubscribe(String pattern, int subscribedChannels) {
		}

		public void onPMessage(String pattern, String channel, String message) {
		}
	}

}
