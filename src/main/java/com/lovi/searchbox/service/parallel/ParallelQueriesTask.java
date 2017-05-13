package com.lovi.searchbox.service.parallel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.RecursiveTask;
import com.lovi.searchbox.query.Query;
import com.lovi.searchbox.service.AsyncSearchBoxOperations;
import com.lovi.searchbox.service.search.SearchResult;
import rx.Observable;
import rx.Single;

class ParallelQueriesTask extends RecursiveTask<Observable<SearchResult<?>>>{

	private static final long serialVersionUID = 9139302166395761908L;
	
	private AsyncSearchBoxOperations searchBoxOperations;
	private Map<Class<?>, Query> queryMap;
	private boolean compute;
	
	ParallelQueriesTask(AsyncSearchBoxOperations searchBoxOperations, Map<Class<?>, Query> queryMap){
		this(searchBoxOperations, queryMap, false);
	}
	
	private ParallelQueriesTask(AsyncSearchBoxOperations searchBoxOperations, Map<Class<?>, Query> queryMap, boolean compute){
		this.searchBoxOperations = searchBoxOperations;
		this.queryMap = queryMap;
		this.compute = compute;
	}
	
	

	@Override
	protected Observable<SearchResult<?>> compute() {
		
		if(!compute){
			
			return Observable.from(invokeAll(createSubs())).flatMap(i->{
				return i.join();
			});
			
		}else
			return process();
	}
	
	@SuppressWarnings("unchecked")
	private Observable<SearchResult<?>> process(){
		
		Observable<SearchResult<?>> searchResults = null;
		
		if(!queryMap.isEmpty()){
			
			Class<?> queryClass = null;
			Query query = null;
			
			for(Entry<Class<?>, Query> entry : queryMap.entrySet()){
				queryClass = entry.getKey();
				query = entry.getValue();
				break;
			}
			
			Single<?> singleSearchResult = searchBoxOperations.search(queryClass, query);
			searchResults = (Observable<SearchResult<?>>) singleSearchResult.toObservable();
			
			searchBoxOperations.search(queryClass, query);
			
		}
		
		return searchResults;
		
	}
	
	private List<ParallelQueriesTask> createSubs(){
		
		List<ParallelQueriesTask> tasks = new ArrayList<>();	
		
		for(Entry<Class<?>, Query> entry : queryMap.entrySet()){
			Map<Class<?>, Query> _queryMap = new HashMap<>();
			_queryMap.put(entry.getKey(), entry.getValue());
			
			ParallelQueriesTask parallelQueriesTask = new ParallelQueriesTask(searchBoxOperations, _queryMap, true);
			tasks.add(parallelQueriesTask);
		}
		
		return tasks;
		
	}

}
