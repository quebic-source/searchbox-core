package com.lovi.searchbox.service.search;

import java.util.Collection;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
public class SearchResult <E>{

	private String modelName;
	private Collection<E> result;
	private Page page;
	private int documentsCount;
	private String time; 
	
	public SearchResult() {
	}

	public SearchResult(String modelName, Collection<E> result, Page page, int documentsCount, String time) {
		this.modelName = modelName;
		this.result = result;
		this.page = page;
		this.documentsCount = documentsCount;
		this.time = time;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Collection<E> getResult() {
		return result;
	}

	public void setResult(Collection<E> result) {
		this.result = result;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getDocumentsCount() {
		return documentsCount;
	}

	public void setDocumentsCount(int documentsCount) {
		this.documentsCount = documentsCount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
