package com.quebic.searchbox.service.parallel;

import com.quebic.searchbox.query.Query;

class QueryPair {

	private Class<?> queryClass;
	private Query query;
	
	public QueryPair(Class<?> queryClass, Query query) {
		this.queryClass = queryClass;
		this.query = query;
	}
	public Class<?> getQueryClass() {
		return queryClass;
	}
	public void setQueryClass(Class<?> queryClass) {
		this.queryClass = queryClass;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	
	
	
}
