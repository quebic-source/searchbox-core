package com.lovi.searchbox.redis.impl;

import static com.lovi.searchbox.config.ConfigKeys.auto_complete_table_perfix;
import static com.lovi.searchbox.config.ConfigKeys.index_table_perfix;
import java.util.ArrayList;
import java.util.List;
import com.lovi.searchbox.common.lua.CommonFunctionNames;
import com.lovi.searchbox.common.util.StringOperation;
import com.lovi.searchbox.config.SearchBoxConfig;
import com.lovi.searchbox.redis.JedisConnectionPool;
import com.lovi.searchbox.redis.ScriptProcessor;
import com.lovi.searchbox.redis.SearchDataProcessor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public final class LuaSearchDataProcessorImpl implements SearchDataProcessor{

	private JedisPool pool = null;
	private SearchBoxConfig config;
	private ScriptProcessor scriptProcessor;

	public LuaSearchDataProcessorImpl(SearchBoxConfig config, JedisConnectionPool jedisConnectionPool, ScriptProcessor scriptProcessor) throws Exception {
		this.pool = jedisConnectionPool.getConnection();
		this.config = config;
		this.scriptProcessor = scriptProcessor;
	}

	@Override
	public <T> void prepareSearchData(Class<T> modelClass, String field,
			String fieldValue, String id) throws Exception {
		prepareSearchData(modelClass.getSimpleName(), field, fieldValue, id);
	}
	
	@Override
	public void prepareSearchData(String modelName, String field,
			String fieldValue, String id) throws Exception {

		try(Jedis jedis = pool.getResource()){

			String autoCompleteValue = fieldValue;
			autoCompleteValue = StringOperation.toLowerCase(autoCompleteValue);

			String autocompleteTableKey = modelName + ":" + field;
			String indexTableKey = modelName + ":" + field + ":" + StringOperation.prepareSpace(fieldValue);

			String zset_autocomplete_table_key = createKey(auto_complete_table_perfix, autocompleteTableKey);
			String zset_index_table_key = createKey(index_table_perfix, indexTableKey);
			
			List<String> keys = new ArrayList<>();
	    	keys.add(zset_autocomplete_table_key);
	    	keys.add(zset_index_table_key);
	    	
	    	List<String> argv = new ArrayList<>();
	    	argv.add(autoCompleteValue);
	    	argv.add(id);
			
	    	scriptProcessor.runScriptByEvalsha(CommonFunctionNames.redis_prepare_search_data_script, keys, argv);
			
		}

	}
	
	private String createKey(String prefix, String name) {
		return config.getAppname() + ":" + prefix + ":" + name;
	}
	
}
