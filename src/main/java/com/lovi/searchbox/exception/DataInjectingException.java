package com.lovi.searchbox.exception;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
public class DataInjectingException extends Exception{

	private static final long serialVersionUID = -34821712046223570L;

	public DataInjectingException(Exception message) {
		super(message);
	}
}
