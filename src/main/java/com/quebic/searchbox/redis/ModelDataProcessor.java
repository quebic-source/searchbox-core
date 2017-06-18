package com.quebic.searchbox.redis;

import com.quebic.searchbox.config.SearchBoxConfig;
import com.quebic.searchbox.redis.impl.ModelDataProcessorImpl;

public interface ModelDataProcessor {
	
	public static ModelDataProcessor create(SearchBoxConfig config, JedisConnectionPool jedisConnectionPool, ScriptProcessor scriptProcessor) {
		return new ModelDataProcessorImpl(config, jedisConnectionPool, scriptProcessor);
	}
	
	public String getModelById(String modelName, String id) throws Exception;
	
	public void storeModel(String modelName, String id, String object) throws Exception;
	
	public void removeModelById(String modelName, String id) throws Exception;
	
	public void clearAllForApp() throws Exception;
	
}
