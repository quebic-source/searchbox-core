package com.lovi.searchbox.redis;

import com.lovi.searchbox.config.SearchBoxConfig;
import com.lovi.searchbox.redis.impl.LuaSearchDataProcessorImpl;

public interface SearchDataProcessor {

	public static SearchDataProcessor create(SearchBoxConfig config, JedisConnectionPool jedisConnectionPool, ScriptProcessor scriptProcessor) throws Exception {
		return new LuaSearchDataProcessorImpl(config, jedisConnectionPool, scriptProcessor);
	}

	<T> void prepareSearchData(Class<T> modelClass, String field, String fieldValue, String id)
			throws Exception;
	
	void prepareSearchData(String modelName, String field, String fieldValue, String id) throws Exception;

}
