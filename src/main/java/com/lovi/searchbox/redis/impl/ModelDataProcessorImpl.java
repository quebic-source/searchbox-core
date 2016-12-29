package com.lovi.searchbox.redis.impl;

import static com.lovi.searchbox.config.ConfigKeys.table_perfix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lovi.searchbox.common.lua.CommonFunctionNames;
import com.lovi.searchbox.config.ConfigKeys;
import com.lovi.searchbox.config.SearchBoxConfig;
import com.lovi.searchbox.redis.JedisConnectionPool;
import com.lovi.searchbox.redis.ModelDataProcessor;
import com.lovi.searchbox.redis.ScriptProcessor;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

public final class ModelDataProcessorImpl implements ModelDataProcessor{
	
    private JedisPool pool = null;
    private SearchBoxConfig config;
    private ScriptProcessor scriptProcessor;
	
	public ModelDataProcessorImpl(SearchBoxConfig config, JedisConnectionPool jedisConnectionPool, ScriptProcessor scriptProcessor) {
		this.pool = jedisConnectionPool.getConnection();
		this.config = config;
		this.scriptProcessor = scriptProcessor;
	}
	
	@Override
	public String getModelById(String modelName, String id) throws Exception {
		try(Jedis jedis = pool.getResource()){
			
			String hm_key = createKey(table_perfix, modelName);
			List<String> result = jedis.hmget(hm_key, id);
			
			if(!result.isEmpty())
				return result.get(0);
			else
				return null;
			
		}
	}
	
	@Override
	public void storeModel(String modelName, String id, String object) throws Exception{

        try(Jedis jedis = pool.getResource()){
        	
    		Map<String, String> map = new HashMap<>();
    		map.put(id, object);
    		
    		String hm_key = createKey(table_perfix, modelName);
    		
    		while(true){
	    		jedis.watch(hm_key);
	    		Transaction transaction = jedis.multi();
	    		transaction.hmset(hm_key, map);
	    		if(transaction.exec() != null)
	    			break;
    		}
 
        }
		
	}
	
	@Override
	public void removeModelById(String modelName, String id) throws Exception {
		
		try(Jedis jedis = pool.getResource()){
        	
			List<String> keys = new ArrayList<>();
			keys.add(ConfigKeys.table_perfix);
			keys.add(ConfigKeys.index_table_perfix);
			keys.add(ConfigKeys.auto_complete_table_perfix);
	    	
	    	List<String> argv = new ArrayList<>();
	    	argv.add(config.getAppname());
	    	argv.add(modelName);
	    	argv.add(id);
			
	    	scriptProcessor.runScriptByEvalsha(CommonFunctionNames.redis_remove_model_by_id_script, keys, argv);
 
        }
		
	}
	
	private String createKey(String prefix, String name){
		return config.getAppname() + ":" + prefix + ":" + name;
	}

	@Override
	public void clearAllForApp() throws Exception {
		try(Jedis jedis = pool.getResource()){
        	
        	Set<String> keys = jedis.keys(config.getAppname() + "*");
        	
        	if(keys.size() > 0)
        		jedis.del(keys.toArray(new String[keys.size()]));
            
        }
	}
	
}
