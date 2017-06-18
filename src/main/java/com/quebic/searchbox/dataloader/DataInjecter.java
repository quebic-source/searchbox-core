package com.quebic.searchbox.dataloader;

import org.springframework.context.ApplicationContext;

import com.quebic.searchbox.dataloader.impl.DataInjecterImpl;
import com.quebic.searchbox.exception.DataInjectingException;

public interface DataInjecter {

	static DataInjecter create(ApplicationContext applicationContext) throws Exception{
		return new DataInjecterImpl(applicationContext);
	}
	
	void start(DataLoaderContainer dataLoaderContainer)throws DataInjectingException;
}
