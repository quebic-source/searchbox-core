package com.lovi.searchbox.dataloader;

import java.util.Collection;

import com.lovi.searchbox.exception.DataLoadingException;

public interface DataLoadHandler<T>{
	Collection<T> handle() throws DataLoadingException;
}
