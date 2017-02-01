package com.lovi.searchbox.launcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.lovi.searchbox.config.HttpServerConfig;
import com.lovi.searchbox.config.RedisConfig;
import com.lovi.searchbox.config.SearchBoxConfig;
import com.lovi.searchbox.query.QueryContainer;
import com.lovi.searchbox.redis.JedisConnectionPool;
import com.lovi.searchbox.service.SearchBoxOperations;
import com.lovi.searchbox.service.impl.SearchBoxOperationsImpl;

@Configuration
@EnableCaching
public class SearchBoxCoreConfiguration{
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public SearchBoxConfig redBoxConfig(){
		return new SearchBoxConfig();
	}
	
	@Bean
	public HttpServerConfig httpServerConfig(){
		return new HttpServerConfig();
	}
	
	@Bean
	public RedisConfig redisConfig(){
		return new RedisConfig();
	}
	
	@Bean
	public JedisConnectionPool jedisConnectionPool(RedisConfig redisConfig){
		return new JedisConnectionPool(redisConfig);
	}
	
	@Bean
	public QueryContainer queryContainer(){
		return new QueryContainer();
	}
	
	@Bean
	public SearchBoxOperations redBoxService(SearchBoxConfig redBoxConfig, JedisConnectionPool jedisConnectionPool, QueryContainer queryContainer) throws Exception{
		return new SearchBoxOperationsImpl(redBoxConfig, jedisConnectionPool, queryContainer);
	}
	
	@Bean
	public SearchBoxLauncher redboxLauncher(SearchBoxOperations redBoxService) throws Exception{
		return new SearchBoxLauncher(applicationContext);
	}
	
}
