package com.quebic.searchbox.common.util;

import java.util.Locale;

public class StringOperation {

	public static String toLowerCase(String in){
		return in.toLowerCase(Locale.getDefault());
	}
	
	public static String prepareSpace(String in){
		return in.replaceAll("\\s+","_SPACE_");
	}
}
