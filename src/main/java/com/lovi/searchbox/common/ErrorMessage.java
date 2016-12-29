package com.lovi.searchbox.common;

public class ErrorMessage {
	public static final String annotation_id_not_found = "unable to find @ID field";
	public static final String id_field_null = "@ID field cannot br null";
	public static final String id_duplicate = "duplicate id";
	public static final String id_value_not_found = "id value not found";
	
	public static final String redis_server_connection_error = "unable to connect redis server";
	public static final String query_function_not_found = "unable to found query function {functionName: %1$s}";
	public static final String query_function_parms_not_found = "unable to found query parameters {functionName: %1$s, parms: %2$s}";
	public static final String input_request_parms_not_found = "unable to found input request parms : %1$s";
}
