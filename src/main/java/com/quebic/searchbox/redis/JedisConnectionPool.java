package com.quebic.searchbox.redis;

import redis.clients.jedis.JedisPool;

public interface JedisConnectionPool {
	public JedisPool getConnection();
}
