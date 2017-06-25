package com.quebic.searchbox.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quebic.searchbox.common.async.response.AsyncResponseEntity;
import com.quebic.searchbox.common.util.AsyncControllerBase;
import rx.Single;

@RestController
@RequestMapping("/common/search")
public class CommonController extends AsyncControllerBase{

	@RequestMapping
	public AsyncResponseEntity<String> test(){
		return makeAsyncResponse(Single.just("go"));
	}
}
