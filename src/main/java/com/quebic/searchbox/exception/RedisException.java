package com.quebic.searchbox.exception;

/**
 * 
 * @author Tharanga Thennakoon
 * @see <a href="https://github.com/loviworld/searchbox-core">https://github.com/loviworld/searchbox-core</a>
 *
 */
public class RedisException extends Exception{

	private static final long serialVersionUID = 7692855017336965645L;

	public RedisException(String message, Exception e) {
		super(message, e);
	}
	
}
