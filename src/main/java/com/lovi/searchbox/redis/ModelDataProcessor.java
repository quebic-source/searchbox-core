package com.lovi.searchbox.redis;

import com.lovi.searchbox.config.SearchBoxConfig;
import com.lovi.searchbox.redis.impl.ModelDataProcessorImpl;

public interface ModelDataProcessor {
	
	public static ModelDataProcessor create(SearchBoxConfig config, JedisConnectionPool jedisConnectionPool, ScriptProcessor scriptProcessor) {
		return new ModelDataProcessorImpl(config, jedisConnectionPool, scriptProcessor);
	}
	
	public String getModelById(String modelName, String id) throws Exception;
	
	public void storeModel(String modelName, String id, String object) throws Exception;
	
	public void removeModelById(String modelName, String id) throws Exception;
	
	public void clearAllForApp() throws Exception;
	
}
