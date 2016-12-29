package com.lovi.searchbox.query;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
public class QueryHolder{

	private Class<?> modelType;
	private Query query;

	public QueryHolder(Class<?> modelType, Query query) {
		this.modelType = modelType;
		this.query = query;
	}
	public Class<?> getModelType() {
		return modelType;
	}
	public void setModelType(Class<?> modelType) {
		this.modelType = modelType;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}
	
}
