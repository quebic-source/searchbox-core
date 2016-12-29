package com.lovi.searchbox.api.dto;

import java.util.HashMap;
import java.util.Map;

import com.lovi.searchbox.service.search.Page;

public class QueryDto {

	private String name;
	private Map<String, Object> parms = new HashMap<>();
	private Page page = new Page();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object> getParms() {
		return parms;
	}
	public void setParms(Map<String, Object> parms) {
		this.parms = parms;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

}
