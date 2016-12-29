package com.lovi.searchbox.dataloader.impl;

import java.util.ArrayList;
import java.util.List;

import com.lovi.searchbox.dataloader.DataLoadHandler;
import com.lovi.searchbox.dataloader.DataLoaderContainer;

public class DataLoaderContainerImpl implements DataLoaderContainer {

	private List<DataLoadHandler<?>> dataLoaders = new ArrayList<>();
	
	@Override
	public <T> DataLoaderContainer addHandler(DataLoadHandler<T> handler) {
		dataLoaders.add(handler);
		return this;
	}

	@Override
	public List<DataLoadHandler<?>> getDataLoaders() {
		return dataLoaders;
	}
	

}
