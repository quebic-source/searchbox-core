package com.lovi.searchbox.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lovi.searchbox.config.RedisConfig;

import redis.clients.jedis.JedisPool;

@Component
public class JedisConnectionPool {
	
	private JedisPool pool;
	
	@Autowired
	public JedisConnectionPool(RedisConfig redisConfig) {
		pool = new JedisPool(redisConfig.getHost(), redisConfig.getPort());
	}
	
	public JedisPool getConnection(){
		return pool;
	}
	
}
