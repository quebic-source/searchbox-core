package com.quebic.searchbox.query.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortImpl<E> implements Sort<E>{
	
	private Comparator<E> comparator;
	
	public SortImpl() {
	}

	public SortImpl(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	@Override
	public Comparator<E> getComparator() {
		return comparator;
	}

	@Override
	public void run(List<E> list) {
		
		if(this.comparator != null)
			Collections.sort(list, comparator);
		
	}

}
