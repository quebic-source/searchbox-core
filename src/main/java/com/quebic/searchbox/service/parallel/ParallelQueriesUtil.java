package com.quebic.searchbox.service.parallel;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import com.quebic.searchbox.service.AsyncSearchBoxOperations;
import com.quebic.searchbox.service.search.SearchResult;

import rx.Observable;

public class ParallelQueriesUtil {

	public static <T> Observable<SearchResult<T>> runForQueryParms(AsyncSearchBoxOperations searchBoxOperations, Map<String, Map<String, Object>> parms){
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		return forkJoinPool.invoke(new ParallelQueryParmsTask<>(searchBoxOperations, parms));
	}
	
	public static Observable<SearchResult<?>> runForQueries(AsyncSearchBoxOperations searchBoxOperations, QueryMap queryMap){
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		return forkJoinPool.invoke(new ParallelQueriesTask(searchBoxOperations, queryMap));
	}
	
}
