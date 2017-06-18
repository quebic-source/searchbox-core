package com.quebic.searchbox.query.sort;

import java.util.Comparator;
import java.util.List;

public interface Sort <E>{

	public static <T> Sort<T> create(Comparator<T> comparator){
		return new SortImpl<T>(comparator);
	}
	
	Comparator<E> getComparator();
	
	void run(List<E> list);

}
