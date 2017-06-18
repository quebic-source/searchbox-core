package com.quebic.searchbox.dataloader;

import java.util.List;

import com.quebic.searchbox.dataloader.impl.DataLoaderContainerImpl;

public interface DataLoaderContainer {

	static DataLoaderContainer create(){
		return new DataLoaderContainerImpl();
	}
	
	<T> DataLoaderContainer addHandler(DataLoadHandler<T> handler);

	List<DataLoadHandler<?>> getDataLoaders();
	
}
