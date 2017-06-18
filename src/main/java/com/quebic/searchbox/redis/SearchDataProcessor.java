package com.quebic.searchbox.redis;

import com.quebic.searchbox.config.SearchBoxConfig;
import com.quebic.searchbox.redis.impl.LuaSearchDataProcessorImpl;

public interface SearchDataProcessor {

	public static SearchDataProcessor create(SearchBoxConfig config, JedisConnectionPool jedisConnectionPool, ScriptProcessor scriptProcessor) throws Exception {
		return new LuaSearchDataProcessorImpl(config, jedisConnectionPool, scriptProcessor);
	}

	<T> void prepareSearchData(Class<T> modelClass, String field, String fieldValue, String id)
			throws Exception;
	
	void prepareSearchData(String modelName, String field, String fieldValue, String id) throws Exception;

}
