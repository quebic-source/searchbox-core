package com.quebic.searchbox.redis;

import java.util.List;

import com.quebic.searchbox.config.SearchBoxConfig;
import com.quebic.searchbox.redis.impl.ScriptProcessorImpl;

public interface ScriptProcessor {

	public static ScriptProcessor create(SearchBoxConfig config, JedisConnectionPool jedisConnectionPool) throws Exception {
		return new ScriptProcessorImpl(config, jedisConnectionPool);
	}
	
	Object runScript(String script, List<String> keys, List<String> argv) throws Exception;
	Object runScriptByEvalsha(String name, List<String> keys, List<String> argv) throws Exception;
	String saveScript(String name, String script) throws Exception;
	
}
