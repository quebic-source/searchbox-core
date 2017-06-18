package com.quebic.searchbox.common.lua;

import java.io.IOException;

public class CommonFunctions {
	
	public static String find_script() throws IOException{
		return LuaScriptUtil.readScript("common/find");
	}
	
	public static String intersection_script() throws IOException{
		return LuaScriptUtil.readScript("common/intersection");
	}
	
	public static String union_script() throws IOException{
		return LuaScriptUtil.readScript("common/union");
	}
	
	public static String difference_script() throws IOException{
		return LuaScriptUtil.readScript("common/difference");
	}
	
	public static String symmetric_script() throws IOException{
		return LuaScriptUtil.readScript("common/symmetric");
	}
	
	public static String is_empty() throws IOException{
		return LuaScriptUtil.readScript("common/is_empty");
	}
	
	public static String sort_set_script() throws IOException{
		return LuaScriptUtil.readScript("common/sort_set");
	}
	
	public static String string_split_script() throws IOException{
		return LuaScriptUtil.readScript("common/string/split");
	}
	
	public static String string_split_to_words_script() throws IOException{
		return LuaScriptUtil.readScript("common/string/split_to_words");
	}
	
	public static String string_remove_spaces_script() throws IOException{
		return LuaScriptUtil.readScript("common/string/remove_spaces");
	}
	
	public static String json_get_value_from_json_script() throws IOException{
		return LuaScriptUtil.readScript("common/json/get_value_from_json");
	}
	
	public static String redis_get_models_by_ids_script() throws IOException{
		return LuaScriptUtil.readScript("redis/get_models_by_ids");
	}
	
	public static String redis_get_models_eq_script() throws IOException{
		return LuaScriptUtil.readScript("redis/get_models_eq");
	}
	
	public static String redis_get_models_ne_script() throws IOException{
		return LuaScriptUtil.readScript("redis/get_models_ne");
	}
	
	public static String redis_get_models_by_pattern_script() throws IOException{
		return LuaScriptUtil.readScript("redis/get_models_by_pattern");
	}
	
	public static String redis_get_models_by_range_script() throws IOException{
		return LuaScriptUtil.readScript("redis/get_models_by_range");
	}
	
	public static String redis_zrangebylex_script() throws IOException{
		return LuaScriptUtil.readScript("redis/zrangebylex");
	}
	
	public static String redis_projection_script() throws IOException{
		return LuaScriptUtil.readScript("redis/projection");
	}
	
	public static String redis_prepare_search_data_script() throws IOException{
		
		StringBuilder stmtStringBuilder = new StringBuilder();

		stmtStringBuilder.append(CommonFunctions.string_split_to_words_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(LuaScriptUtil.readScript("redis/prepare_search_data"));
		stmtStringBuilder.append("\n");
		
		return stmtStringBuilder.toString();
		
	}
	
	public static String redis_search_script() throws IOException{
		
		StringBuilder stmtStringBuilder = new StringBuilder();
		
		stmtStringBuilder.append(CommonFunctions.redis_get_models_by_ids_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(LuaScriptUtil.readScript("redis/search"));
		stmtStringBuilder.append("\n");
		
		return stmtStringBuilder.toString();
		
	}
	
	public static String redis_search_by_perfix_script() throws IOException{
		
		StringBuilder stmtStringBuilder = new StringBuilder();
		
		stmtStringBuilder.append(CommonFunctions.string_split_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.string_split_to_words_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_zrangebylex_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(CommonFunctions.redis_get_models_by_ids_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(LuaScriptUtil.readScript("redis/search_by_perfix"));
		stmtStringBuilder.append("\n");
		
		return stmtStringBuilder.toString();
		
	}
	
	public static String redis_remove_model_by_id_script() throws IOException{
		
		StringBuilder stmtStringBuilder = new StringBuilder();
		
		stmtStringBuilder.append(CommonFunctions.string_remove_spaces_script());
		stmtStringBuilder.append("\n");
		stmtStringBuilder.append(LuaScriptUtil.readScript("redis/remove_model_by_id"));
		stmtStringBuilder.append("\n");
		
		return stmtStringBuilder.toString();
		
	}	
	
}
