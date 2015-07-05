/*
 * DataAccessException.java
 * Created on 10/08/2003
 *
 */
package edu.unsw.comp9321.exception;


public class DataAccessException extends RuntimeException {


	/**
	 * @param message
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
