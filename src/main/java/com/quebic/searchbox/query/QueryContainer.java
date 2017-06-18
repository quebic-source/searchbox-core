package com.quebic.searchbox.query;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
@Component
public class QueryContainer {

	private Map<String, QueryHolder> queryMap = new ConcurrentHashMap<>();
	
	public void putQuery(String name, QueryHolder query){
		queryMap.put(name, query);
	}
	
	public QueryHolder getQuery(String name){
		return queryMap.get(name);
	}
	
}
