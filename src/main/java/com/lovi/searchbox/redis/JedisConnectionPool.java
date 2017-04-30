package com.lovi.searchbox.redis;

import redis.clients.jedis.JedisPool;

public interface JedisConnectionPool {
	public JedisPool getConnection();
}
