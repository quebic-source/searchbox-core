package com.quebic.searchbox.launcher;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.quebic.searchbox.common.async.handlers.AsyncResponseEntityReturnHandler;
import com.quebic.searchbox.common.controller.CommonController;
import com.quebic.searchbox.config.HttpServerConfig;
import com.quebic.searchbox.config.HttpServerCustomizer;
import com.quebic.searchbox.config.RedisConfig;
import com.quebic.searchbox.config.SearchBoxConfig;
import com.quebic.searchbox.query.QueryContainer;
import com.quebic.searchbox.redis.JedisConnectionPool;
import com.quebic.searchbox.redis.impl.JedisConnectionPoolImpl;
import com.quebic.searchbox.service.AsyncSearchBoxOperations;
import com.quebic.searchbox.service.SearchBoxOperations;
import com.quebic.searchbox.service.impl.AsyncSearchBoxOperationsImpl;
import com.quebic.searchbox.service.impl.SearchBoxOperationsImpl;

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
		return new JedisConnectionPoolImpl(redisConfig);
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
	public AsyncSearchBoxOperations asyncsearchboxService(SearchBoxOperations searchBoxOperations) throws Exception{
		return new AsyncSearchBoxOperationsImpl(searchBoxOperations);
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
	
	@Bean
	public AsyncResponseEntityReturnHandler asyncResponseEntityReturnHandler() {
        return new AsyncResponseEntityReturnHandler();
    }
	
	@Bean
    public WebMvcConfigurer rxJavaWebMvcConfiguration(List<AsyncHandlerMethodReturnValueHandler> handlers) {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
                if (handlers != null) {
                    returnValueHandlers.addAll(handlers);
                }
            }
        };
    }
	
	@Bean
	public CommonController commonController(){
		return new CommonController();
	}
	
}
