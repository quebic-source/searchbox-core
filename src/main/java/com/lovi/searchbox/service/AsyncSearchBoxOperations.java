package com.lovi.searchbox.service;

import java.util.Map;
import com.lovi.searchbox.query.Query;
import com.lovi.searchbox.service.parallel.ParallelResult;
import com.lovi.searchbox.service.parallel.QueryMap;
import com.lovi.searchbox.service.search.Page;
import com.lovi.searchbox.service.search.SearchResult;

import rx.Observable;
import rx.Single;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
public interface AsyncSearchBoxOperations {

	<T> Single<Boolean> insert(T object);
	<T> Single<Boolean> update(T object);
	<T> Single<Boolean> save(T object);
	
	<T> Single<Boolean> delete(T object);
	<T> Single<Boolean> delete(Class<T> cls, Object id);
	
	<T> Single<SearchResult<T>> search(Class<T> cls, Query query);
	<T> Single<SearchResult<T>> search(Class<T> cls, Query query, Page page);
	
	<T> Single<SearchResult<T>> search(String queryName);
	<T> Single<SearchResult<T>> search(String queryName, Map<String, Object> inputParms);
	<T> Single<SearchResult<T>> search(String queryName, Map<String, Object> inputParms, Page page);
	
	<T> Single<SearchResult<T>> searchByField(Class<T> cls, String field, Object searchValue);
	<T> Single<SearchResult<T>> searchByField(Class<T> cls, String field, Object searchValue, Page page);
	
	<T> Single<SearchResult<T>> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix);
	<T> Single<SearchResult<T>> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix, boolean allWords);
	<T> Single<SearchResult<T>> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix, Page page);
	<T> Single<SearchResult<T>> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix, Page page, boolean allWords);
	
	<T> Single<SearchResult<T>> searchByFieldPattern(Class<T> cls, String field, String pattern);
	<T> Single<SearchResult<T>> searchByFieldPattern(Class<T> cls, String field, String pattern, Page page);
	
	<T> Observable<SearchResult<T>> parallelQueries(Map<String, Map<String, Object>> parms);
	Observable<ParallelResult> parallelQueries(QueryMap queryMap);
	
}
