package com.lovi.searchbox.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.RecursiveTask;
import com.lovi.searchbox.service.SearchBoxOperations;
import com.lovi.searchbox.service.search.SearchResult;

public class ParallelQueriesTask <E> extends RecursiveTask<List<SearchResult<E>>>{

	private static final long serialVersionUID = 9139302166395761908L;
	
	private SearchBoxOperations searchBoxOperations;
	private Map<String, Map<String, Object>> queryparms;
	
	ParallelQueriesTask(SearchBoxOperations searchBoxOperations, Map<String, Map<String, Object>> parms){
		this(searchBoxOperations, parms, false);
	}
	
	private ParallelQueriesTask(SearchBoxOperations searchBoxOperations, Map<String, Map<String, Object>> parms, boolean compute){
		this.searchBoxOperations = searchBoxOperations;
		this.queryparms = parms;
	}

	@Override
	protected List<SearchResult<E>> compute() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<SearchResult<E>> process(){
		
		List<SearchResult<E>> searchResults = new ArrayList<>();
		
		if(!queryparms.isEmpty()){
			
			for(Entry<String, Map<String, Object>> entry : queryparms.entrySet()){
				String queryName = entry.getKey();
				Map<String, Object> parms = entry.getValue();
			}
			
		}
		
		return searchResults;
		
	}

}
