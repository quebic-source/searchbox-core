package com.lovi.searchbox.common.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import com.lovi.searchbox.common.async.response.AsyncResponseEntity;
import rx.Observable;
import rx.Single;

public class AsyncControllerBase {

	protected <T> ResponseEntity<T> makeResponse(T message) {
		return makeResponse(message, null, HttpStatus.OK);
	}

	protected <T> ResponseEntity<T> makeResponse(T message, HttpStatus status) {
		return makeResponse(message, null, status);
	}
	
	protected <T> ResponseEntity<T> makeResponse(T message, MultiValueMap<String, String> headers, HttpStatus status) {
		return new ResponseEntity<T>(message, headers, status);
	}

	/*
	 * Observable
	 */
	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Observable<T> observable) {
		return makeAsyncResponse(observable, null, HttpStatus.OK);
	}

	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Observable<T> observable, HttpStatus status) {
		return makeAsyncResponse(observable, null, status);
	}
	
	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Observable<T> observable, MultiValueMap<String, String> headers,HttpStatus status) {
		return AsyncResponseEntity.<T>status(status).headers(headers).observable(observable);
	}
	/*
	 * Observable
	 */
	
	/*
	 * Single
	 */
	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Single<T> single) {
		return makeAsyncResponse(single, null, HttpStatus.OK);
	}
	
	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Single<T> single, HttpStatus status) {
		return makeAsyncResponse(single, null, status);
	}

	protected <T> AsyncResponseEntity<T> makeAsyncResponse(Single<T> single, MultiValueMap<String, String> headers,HttpStatus status) {
		return AsyncResponseEntity.<T>status(status).headers(headers).single(single);
	}
	/*
	 * Single
	 */

}
