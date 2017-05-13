package com.lovi.searchbox.service.parallel;

import java.util.ArrayList;
import java.util.List;

import com.lovi.searchbox.query.Query;

public class QueryMap {

	private List<QueryPair> queryPairs;
	
	private QueryMap(){
		this.queryPairs = new ArrayList<>();
	}
	
	public static QueryMap create(){
		return new QueryMap();
	}
	
	public QueryMap add(Class<?> queryClass, Query query){
		queryPairs.add(new QueryPair(queryClass, query));
		return this;
	}

	public List<QueryPair> getQueryPairs() {
		return queryPairs;
	}
	
}
