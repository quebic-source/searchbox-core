package com.lovi.searchbox.query.impl;

import static com.lovi.searchbox.config.ConfigKeys.auto_complete_table_perfix;
import static com.lovi.searchbox.config.ConfigKeys.index_table_perfix;
import static com.lovi.searchbox.config.ConfigKeys.lua_input_parm_key;
import static com.lovi.searchbox.config.ConfigKeys.lua_input_parm_value;

import java.util.ArrayList;
import java.util.List;

import com.lovi.searchbox.query.Criteria;

public class FieldCriteria extends Criteria {

	public FieldCriteria(String key) {
		this.key = key;
		criteriaChain = new ArrayList<>();	
	}
	
	public FieldCriteria(String key, List<String> criteriaChain) {
		this.key = key;
		this.criteriaChain = criteriaChain;
	}
	
	@Override
	public String getScript() {
		String parm = getParameterName();
		
		String stmt = null;
		
		switch (conditionType) {
		case EQUAL:
			stmt = String.format("local %1$s = get_models_eq(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s .. ':' .. remove_spaces(%4$s))", parm, index_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));	
			break;
		
		case NOT_EQUAL:
			stmt = String.format("local %1$s = get_models_ne(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s, remove_spaces(%4$s))", parm, index_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));	
			break;
			
		case PREFIX_ONE_WORD:
			stmt = String.format("local %1$s = redis_zrangebylex(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s, string.lower(%4$s), false)", parm, auto_complete_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));	
			break;
		
		case PREFIX_ALL_WORDS:
			stmt = String.format("local %1$s = redis_zrangebylex(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s, string.lower(%4$s), true)", parm, auto_complete_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));	
			break;
			
		case PATTERN:
			stmt = String.format("local %1$s = get_models_by_pattern(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s, remove_spaces(%4$s))", parm, index_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));
			break;
			
		case GT:
			stmt = String.format("local %1$s = get_models_by_range(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s, 'gt', remove_spaces(%4$s))", parm, index_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));
			break;
			
		case GTE:
			stmt = String.format("local %1$s = get_models_by_range(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s, 'gte', remove_spaces(%4$s))", parm, index_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));
			break;
			
		case LT:
			stmt = String.format("local %1$s = get_models_by_range(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s, 'lt', remove_spaces(%4$s))", parm, index_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));
			break;
			
		case LTE:
			stmt = String.format("local %1$s = get_models_by_range(app_name .. ':%2$s:' .. model_name .. ':' .. %3$s, 'lte', remove_spaces(%4$s))", parm, index_table_perfix, prepare_KEYS(lua_input_parm_key), prepare_KEYS(lua_input_parm_value));
			break;

		default:
			break;
		}
		
		return stmt;
	}
	
	public String prepare_KEYS(String s){
		return String.format("get_value_from_json(KEYS[%1$s], '%2$s')", parmId, s);
	}

}
