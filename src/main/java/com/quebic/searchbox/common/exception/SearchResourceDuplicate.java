package com.quebic.searchbox.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Id duplicate")  //404
public class SearchResourceDuplicate extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7940474305315665471L;

}
