package com.quebic.searchbox.query;

import java.util.Arrays;
import java.util.List;

import com.quebic.searchbox.common.lua.CommonFunctions;
import com.quebic.searchbox.config.ConfigKeys;
import com.quebic.searchbox.query.sort.Sort;
import com.quebic.searchbox.service.search.Page;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
public class Query {

	private Criteria criteria;

	private Page page;
	
	private String queryName;

	private Projection projection;
	
	public Query(Criteria criteria) {
		this(criteria, "");
	}
	
	public Query(Criteria criteria, String queryName) {
		this.criteria = criteria;
		this.page = new Page();
		this.queryName = queryName;
		this.projection = new Projection();
	}

	public Criteria getCriteria() {
		return criteria;
	}

	public String getQueryString() throws Exception {
		StringBuilder stmtStringBuilder = new StringBuilder();

		// append common lua functions
		stmtStringBuilder.append(CommonFunctions.find_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.intersection_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.union_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.difference_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.symmetric_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.is_empty());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.sort_set_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.string_split_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.string_split_to_words_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.string_remove_spaces_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.json_get_value_from_json_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_projection_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_get_models_eq_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_get_models_by_ids_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_get_models_by_pattern_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_get_models_by_range_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_zrangebylex_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_get_models_ne_script());
		stmtStringBuilder.append("\n");
		// append common lua functions

		// setting app name
		stmtStringBuilder.append("local app_name = ARGV[1]");
		stmtStringBuilder.append("\n");
		// setting app name

		// setting model name
		stmtStringBuilder.append("local model_name = ARGV[2]");
		stmtStringBuilder.append("\n");
		// setting model name

		// setting page length
		stmtStringBuilder.append("local page_length = tonumber(ARGV[3])");
		stmtStringBuilder.append("\n");
		// setting page length

		// setting page length
		stmtStringBuilder.append("local page_no = tonumber(ARGV[4])");
		stmtStringBuilder.append("\n");
		// setting page length
		
		// setting hideFields
		stmtStringBuilder.append("local hideFields = ARGV[5]");
		stmtStringBuilder.append("\n");
		// setting hideFields

		stmtStringBuilder.append("\n");
		
		// criteria script
		stmtStringBuilder.append(criteria.getScript());
		stmtStringBuilder.append("\n");
		// criteria script
		
		// setting last parameter
		stmtStringBuilder.append("local _parm = " + criteria.getParameterName());
		stmtStringBuilder.append("\n");
		// setting last parameter

		stmtStringBuilder.append("\n");
		
		// get models by ids
		// mapKey -> <app_name>:table:<model_name>
		// get_models_by_ids(mapKey, modelIds, pageLength, pageNo)
		String getModelsStmt = String.format(
				"return get_models_by_ids(app_name .. ':%1$s:' .. model_name, _parm, page_length, page_no, hideFields)",
				ConfigKeys.table_perfix);

		stmtStringBuilder.append(getModelsStmt);
		stmtStringBuilder.append("\n");
		// get models by ids
		
		return stmtStringBuilder.toString();

	}

	public int getPageLength() {
		return page.getPageLength();
	}

	public void setPageLength(int pageLength) {
		this.page.setPageLength(pageLength);
	}

	public int getPageNo() {
		return page.getPageNo();
	}

	public void setPageNo(int pageNo) {
		this.page.setPageNo(pageNo);
	}
	
	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}



	/**
	 * ---Sort---
	 */
	@SuppressWarnings("rawtypes")
	private Sort sort;
	
	public <T> Query sort(Sort<T> sort){
		this.sort = sort;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public Sort getSort(){
		return this.sort;
	}
	
	/**
	 * ---Sort---
	 */
	
	/**
	 * ---Projection---
	 */
	public Projection getProjection() {
		return projection;
	}
	
	public Query setHideField(String field){
		this.projection.addHideField(field);
		return this;
	}

	public Query setShowField(String field){
		this.projection.addShowField(field);
		return this;
	}
	
	public Query setHideFields(String ...fields){
		return setHideFields(Arrays.asList(fields));
	}
	
	public Query setHideFields(List<String> fields){
		this.projection.addHideFields(fields);
		return this;
	}
	
	public Query setShowFields(String ...fields){
		return setShowFields(Arrays.asList(fields));
	}

	public Query setShowFields(List<String> fields){
		this.projection.addShowFields(fields);
		return this;
	}
	/**
	 * ---Projection---
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((criteria.toString() == null) ? 0 : criteria.toString().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Query other = (Query) obj;
		if (criteria.toString() == null) {
			if (other.getCriteria().toString() != null)
				return false;
		} else if (!criteria.toString().equals(other.getCriteria().toString()))
			return false;
		
		return true;
	}
}
