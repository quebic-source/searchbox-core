package com.lovi.searchbox.service.parallel;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import com.lovi.searchbox.service.AsyncSearchBoxOperations;
import com.lovi.searchbox.service.search.SearchResult;
import rx.Observable;

public class ParallelQueriesUtil {

	public static <T> Observable<SearchResult<T>> run(AsyncSearchBoxOperations searchBoxOperations, Map<String, Map<String, Object>> parms){
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		return forkJoinPool.invoke(new ParallelQueriesTask<>(searchBoxOperations, parms));
	}
	
}
