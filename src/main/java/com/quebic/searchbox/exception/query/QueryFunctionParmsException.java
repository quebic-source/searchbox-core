package com.quebic.searchbox.exception.query;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.quebic.searchbox.common.ErrorMessage;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class QueryFunctionParmsException extends RuntimeException{

	private static final long serialVersionUID = 3350281743907358704L;

	public QueryFunctionParmsException(String queryName, List<String> notFoundParms) {
		super(String.format(
				ErrorMessage.query_function_parms_not_found
				, queryName
				, notFoundParms));
	}
	
}
