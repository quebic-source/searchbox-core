package com.lovi.searchbox.dataloader;

import org.springframework.context.ApplicationContext;

import com.lovi.searchbox.dataloader.impl.DataInjecterImpl;
import com.lovi.searchbox.exception.DataInjectingException;

public interface DataInjecter {

	static DataInjecter create(ApplicationContext applicationContext) throws Exception{
		return new DataInjecterImpl(applicationContext);
	}
	
	void start(DataLoaderContainer dataLoaderContainer)throws DataInjectingException;
}
