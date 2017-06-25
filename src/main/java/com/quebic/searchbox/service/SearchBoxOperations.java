package com.quebic.searchbox.service;

import java.util.List;
import java.util.Map;

import com.quebic.searchbox.exception.SearchBoxOperationsException;
import com.quebic.searchbox.query.Query;
import com.quebic.searchbox.service.search.Page;
import com.quebic.searchbox.service.search.SearchResult;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
public interface SearchBoxOperations {
	
	<T> void insert(T object) throws SearchBoxOperationsException;
	<T> void update(T object) throws SearchBoxOperationsException;
	<T> void save(T object) throws SearchBoxOperationsException;
	
	<T> void delete(T object) throws SearchBoxOperationsException;
	<T> void delete(Class<T> cls, Object id) throws SearchBoxOperationsException;
	
	<T> SearchResult<T> search(Class<T> cls, Query query) throws SearchBoxOperationsException;
	<T> SearchResult<T> search(Class<T> cls, Query query, Page page) throws SearchBoxOperationsException;
	
	<T> SearchResult<T> search(Class<T> cls, String queryJson) throws SearchBoxOperationsException;
	<T> SearchResult<T> search(Class<T> cls, String queryJson, Page page) throws SearchBoxOperationsException;
	
	<T> SearchResult<T> search(String queryName) throws SearchBoxOperationsException;
	<T> SearchResult<T> search(String queryName, Map<String, Object> inputParms) throws SearchBoxOperationsException;
	<T> SearchResult<T> search(String queryName, Map<String, Object> inputParms, Page page) throws SearchBoxOperationsException;
	
	<T> SearchResult<T> searchByField(Class<T> cls, String field, Object searchValue) throws SearchBoxOperationsException;
	<T> SearchResult<T> searchByField(Class<T> cls, String field, Object searchValue, Page page) throws SearchBoxOperationsException;
	
	<T> SearchResult<T> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix) throws SearchBoxOperationsException;
	<T> SearchResult<T> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix, boolean allWords) throws SearchBoxOperationsException;
	<T> SearchResult<T> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix, Page page) throws SearchBoxOperationsException;
	<T> SearchResult<T> searchByFieldPerfix(Class<T> cls, String field, Object searchPrefix, Page page, boolean allWords) throws SearchBoxOperationsException;
	
	<T> SearchResult<T> searchByFieldPattern(Class<T> cls, String field, String pattern) throws SearchBoxOperationsException;
	<T> SearchResult<T> searchByFieldPattern(Class<T> cls, String field, String pattern, Page page) throws SearchBoxOperationsException;
	
	Object runScriptByEvalsha(String name, List<String> keys, List<String> argv) throws SearchBoxOperationsException;
	String saveScript(String name, String script) throws SearchBoxOperationsException;
	
	void clearAllForApp() throws SearchBoxOperationsException;

}
