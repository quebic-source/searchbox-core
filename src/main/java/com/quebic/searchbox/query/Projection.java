package com.quebic.searchbox.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Projection {

	Map<String, Integer> hidefields = null;
	Map<String, Integer> showfields = null;
	
	public Projection addHideField(String field){
		
		if(hidefields == null)
			this.hidefields = new HashMap<>();
		
		this.hidefields.put(field, 1);
		
		return this;
	}
	
	public Projection addShowField(String field){
		
		if(showfields == null)
			this.showfields = new HashMap<>();
		
		this.showfields.put(field, 1);
		
		return this;
	}
	
	public Projection addHideFields(List<String> fields){
		
		for (String field : fields) {
			addHideField(field);
		}
		
		return this;
	}
	
	public Projection addShowFields(List<String> fields){
		
		for (String field : fields) {
			addShowField(field);
		}
		
		return this;
	}

	public Map<String, Integer> getHidefields() {
		return hidefields;
	}

	public Map<String, Integer> getShowfields() {
		return showfields;
	}
	
}
