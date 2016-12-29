package com.lovi.searchbox.exception.query;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.lovi.searchbox.common.ErrorMessage;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class QueryFunctionNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 3350281743907358704L;

	public QueryFunctionNotFoundException(String queryName) {
		super(String.format(
				ErrorMessage.query_function_not_found
				, queryName));
	}
	
}
