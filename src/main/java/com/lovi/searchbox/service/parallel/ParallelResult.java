package com.lovi.searchbox.service.parallel;

import com.lovi.searchbox.service.search.SearchResult;

public class ParallelResult {

	private String queryName;
	private SearchResult<?> searchResult;
	
	public ParallelResult(String queryName, SearchResult<?> searchResult) {
		this.queryName = queryName;
		this.searchResult = searchResult;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public SearchResult<?> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(SearchResult<?> searchResult) {
		this.searchResult = searchResult;
	}
	
	
	
}
