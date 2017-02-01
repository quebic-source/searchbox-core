package com.lovi.searchbox.launcher;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import com.lovi.searchbox.config.HttpServerConfig;
import com.lovi.searchbox.config.HttpServerCustomizer;
import com.lovi.searchbox.config.RedisConfig;
import com.lovi.searchbox.config.SearchBoxConfig;
import com.lovi.searchbox.query.QueryContainer;
import com.lovi.searchbox.redis.JedisConnectionPool;
import com.lovi.searchbox.service.SearchBoxOperations;
import com.lovi.searchbox.service.impl.SearchBoxOperationsImpl;

@Configuration
@EnableCaching
public class SearchBoxConfiguration{
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	public SearchBoxConfig searchboxConfig(){
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
	public HttpServerCustomizer httpServerCustomizer(HttpServerConfig httpServerConfig){
		return new HttpServerCustomizer(httpServerConfig);
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
	public SearchBoxOperations searchboxService(SearchBoxConfig searchboxConfig, JedisConnectionPool jedisConnectionPool, QueryContainer queryContainer) throws Exception{
		return new SearchBoxOperationsImpl(searchboxConfig, jedisConnectionPool, queryContainer);
	}
	
	@Bean
	public SearchBoxLauncher searchboxLauncher(SearchBoxOperations searchboxService) throws Exception{
		return new SearchBoxLauncher(applicationContext);
	}
	
	@Bean
	public ErrorAttributes customizeErrorResponseAttributes() {
	    
		return new DefaultErrorAttributes(){
	    	@Override
	        public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
	            Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
	            errorAttributes.remove("timestamp");
	            errorAttributes.remove("exception");
	            return errorAttributes;
	        }
	    };
	    
	}
}
