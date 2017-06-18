package com.quebic.searchbox.dataloader.impl;

import java.util.Collection;
import org.springframework.context.ApplicationContext;

import com.quebic.searchbox.dataloader.DataInjecter;
import com.quebic.searchbox.dataloader.DataLoadHandler;
import com.quebic.searchbox.dataloader.DataLoaderContainer;
import com.quebic.searchbox.exception.DataInjectingException;
import com.quebic.searchbox.service.SearchBoxOperations;

public class DataInjecterImpl implements DataInjecter {

	private SearchBoxOperations searchBoxOperations;
	
	public DataInjecterImpl(ApplicationContext applicationContext) throws Exception {
		this.searchBoxOperations = applicationContext.getBean(SearchBoxOperations.class);
	}
	
	@Override
	public void start(DataLoaderContainer dataLoaderContainer) throws DataInjectingException {
	
		try{
			
			for(DataLoadHandler<?> dataLoadHandler : dataLoaderContainer.getDataLoaders()){
				
				Collection<?> collection = dataLoadHandler.handle();
				
				for(Object object : collection){
					searchBoxOperations.save(object);
				}
				
				
			}
		}catch(Exception e){
			throw new DataInjectingException(e);
		}
		
	}
	
}
