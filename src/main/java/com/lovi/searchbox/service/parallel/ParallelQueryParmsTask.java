package com.lovi.searchbox.service.parallel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.RecursiveTask;
import org.springframework.util.StringUtils;
import com.lovi.searchbox.service.AsyncSearchBoxOperations;
import com.lovi.searchbox.service.search.SearchResult;
import rx.Observable;
import rx.Single;

class ParallelQueryParmsTask<E> extends RecursiveTask<Observable<SearchResult<E>>>{

	private static final long serialVersionUID = 9139302166395761908L;
	
	private AsyncSearchBoxOperations searchBoxOperations;
	private Map<String, Map<String, Object>> queryparms;
	private boolean compute;
	
	ParallelQueryParmsTask(AsyncSearchBoxOperations searchBoxOperations, Map<String, Map<String, Object>> parms){
		this(searchBoxOperations, parms, false);
	}
	
	private ParallelQueryParmsTask(AsyncSearchBoxOperations searchBoxOperations, Map<String, Map<String, Object>> parms, boolean compute){
		this.searchBoxOperations = searchBoxOperations;
		this.queryparms = parms;
		this.compute = compute;
	}

	@Override
	protected Observable<SearchResult<E>> compute() {
		
		if(!compute){
			
			return Observable.from(invokeAll(createSubs())).flatMap(i->{
				return i.join();
			});
			
		}else
			return process();
	}
	
	private Observable<SearchResult<E>> process(){
		
		Observable<SearchResult<E>> searchResults = null;
		
		if(!queryparms.isEmpty()){
			
			String queryName = null;
			Map<String, Object> inputParms = null;
			
			for(Entry<String, Map<String, Object>> entry : queryparms.entrySet()){
				queryName = entry.getKey();
				inputParms = entry.getValue();
				break;
			}
			
			if(!StringUtils.isEmpty(queryName) && !StringUtils.isEmpty(inputParms)){
				Single<SearchResult<E>> singleSearchResult = searchBoxOperations.search(queryName, inputParms);
				searchResults = singleSearchResult.toObservable();
			}
			
		}
		
		return searchResults;
		
	}
	
	private List<ParallelQueryParmsTask<E>> createSubs(){
		
		List<ParallelQueryParmsTask<E>> tasks = new ArrayList<>();	
		
		for(Entry<String, Map<String, Object>> entry : queryparms.entrySet()){
			Map<String, Map<String, Object>> _queryparms = new HashMap<>();
			_queryparms.put(entry.getKey(), entry.getValue());
			
			ParallelQueryParmsTask<E> parallelQueriesTask = new ParallelQueryParmsTask<>(searchBoxOperations, _queryparms, true);
			tasks.add(parallelQueriesTask);
		}
		
		return tasks;
		
	}

}
