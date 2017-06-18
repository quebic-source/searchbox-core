package com.quebic.searchbox.redis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quebic.searchbox.config.RedisConfig;
import com.quebic.searchbox.redis.JedisConnectionPool;

import redis.clients.jedis.JedisPool;

@Component
public class JedisConnectionPoolImpl implements JedisConnectionPool{

	private JedisPool pool;
	
	@Autowired
	public JedisConnectionPoolImpl(RedisConfig redisConfig) {
		pool = new JedisPool(redisConfig.getHost(), redisConfig.getPort());
	}
	
	@Override
	public JedisPool getConnection(){
		return pool;
	}
	
}
