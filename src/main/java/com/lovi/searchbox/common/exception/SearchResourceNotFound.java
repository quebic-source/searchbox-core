package com.lovi.searchbox.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Search object not found")  //404
public class SearchResourceNotFound extends RuntimeException{

	private static final long serialVersionUID = 3911005777490814119L;

}
