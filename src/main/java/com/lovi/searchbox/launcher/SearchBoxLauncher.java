package com.lovi.searchbox.launcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.lovi.searchbox.annotation.process.DataLoaderAnnotationProcessor;
import com.lovi.searchbox.annotation.process.QueryAnnotationProcessor;
import com.lovi.searchbox.common.ErrorMessage;
import com.lovi.searchbox.common.lua.CommonFunctionNames;
import com.lovi.searchbox.common.lua.CommonFunctions;
import com.lovi.searchbox.config.SearchBoxConfig;
import com.lovi.searchbox.dataloader.DataInjecter;
import com.lovi.searchbox.dataloader.DataLoaderContainer;
import com.lovi.searchbox.redis.JedisConnectionPool;
import com.lovi.searchbox.service.SearchBoxOperations;

import redis.clients.jedis.exceptions.JedisException;

public class SearchBoxLauncher{
	
	private Logger logger = LoggerFactory.getLogger(SearchBoxLauncher.class);
	
	private SearchBoxConfig config;
	private SearchBoxOperations searchBoxOperations;
	
	@Autowired
	public SearchBoxLauncher(ApplicationContext context) throws Exception {
		
		this.config = context.getBean(SearchBoxConfig.class);
		this.searchBoxOperations = context.getBean(SearchBoxOperations.class);
		
		checkRedisConnection(context);
		clearAllForApp();
		loadRedisFunctions();
		dataLoader(context);
		queryLoader(context);
	}

	private void checkRedisConnection(ApplicationContext context){
		
		try{
			context.getBean(JedisConnectionPool.class).getConnection().getResource();
		}catch(JedisException e){
			logger.error(ErrorMessage.redis_server_connection_error + ", {}", e.getMessage());
		}
		
	}
	
	private void clearAllForApp() throws Exception {
		if(config.getDataloader().isClearAll()){
			logger.info("clear all data");
			searchBoxOperations.clearAllForApp();
		}
	}
	
	private void loadRedisFunctions() throws Exception {
		
		searchBoxOperations.saveScript(CommonFunctionNames.redis_prepare_search_data_script, CommonFunctions.redis_prepare_search_data_script());
		searchBoxOperations.saveScript(CommonFunctionNames.redis_search_script, CommonFunctions.redis_search_script());
		searchBoxOperations.saveScript(CommonFunctionNames.redis_search_by_perfix_script, CommonFunctions.redis_search_by_perfix_script());
		searchBoxOperations.saveScript(CommonFunctionNames.redis_remove_model_by_id_script, CommonFunctions.redis_remove_model_by_id_script());
		
	}
	
	private void dataLoader(ApplicationContext context) throws Exception{
		DataLoaderContainer dataLoaderContainer = DataLoaderContainer.create();
    	
		DataLoaderAnnotationProcessor dataLoaderAnnotationProcessor = new DataLoaderAnnotationProcessor(context);
		dataLoaderAnnotationProcessor.start(dataLoaderContainer); 
		
		DataInjecter dataInjecter = DataInjecter.create(context);
		dataInjecter.start(dataLoaderContainer);
	}
	
	private void queryLoader(ApplicationContext context) throws Exception{
		QueryAnnotationProcessor queryAnnotationProcessor = new QueryAnnotationProcessor(context);
		queryAnnotationProcessor.start();
	}
	
}
