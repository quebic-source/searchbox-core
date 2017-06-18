package com.quebic.searchbox.dataloader;

import java.util.Collection;

import com.quebic.searchbox.exception.DataLoadingException;

public interface DataLoadHandler<T>{
	Collection<T> handle() throws DataLoadingException;
}
