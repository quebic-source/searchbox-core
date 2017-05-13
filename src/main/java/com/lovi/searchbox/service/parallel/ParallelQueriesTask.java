package com.lovi.searchbox.service.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import com.lovi.searchbox.query.Query;
import com.lovi.searchbox.service.AsyncSearchBoxOperations;
import com.lovi.searchbox.service.search.SearchResult;
import rx.Observable;
import rx.Single;

class ParallelQueriesTask extends RecursiveTask<Observable<SearchResult<?>>>{

	private static final long serialVersionUID = 9139302166395761908L;
	
	private AsyncSearchBoxOperations searchBoxOperations;
	private QueryMap queryMap;
	private boolean compute;
	
	ParallelQueriesTask(AsyncSearchBoxOperations searchBoxOperations, QueryMap queryMap){
		this(searchBoxOperations, queryMap, false);
	}
	
	private ParallelQueriesTask(AsyncSearchBoxOperations searchBoxOperations, QueryMap queryMap, boolean compute){
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
		
		if(queryMap != null && !queryMap.getQueryPairs().isEmpty()){
			
			Class<?> queryClass = null;
			Query query = null;
			
			for(QueryPair queryPair : queryMap.getQueryPairs()){
				queryClass = queryPair.getQueryClass();
				query = queryPair.getQuery();
				break;
			}
			
			Single<?> singleSearchResult = searchBoxOperations.search(queryClass, query);
			searchResults = (Observable<SearchResult<?>>) singleSearchResult.toObservable();
			
		}
		
		return searchResults;
		
	}
	
	private List<ParallelQueriesTask> createSubs(){
		
		List<ParallelQueriesTask> tasks = new ArrayList<>();	
		
		for(QueryPair queryPair : queryMap.getQueryPairs()){
			
			QueryMap _queryMap = QueryMap.create();
			_queryMap.add(queryPair.getQueryClass(), queryPair.getQuery());
			
			ParallelQueriesTask parallelQueriesTask = new ParallelQueriesTask(searchBoxOperations, _queryMap, true);
			tasks.add(parallelQueriesTask);
		}
		
		return tasks;
		
	}

}
