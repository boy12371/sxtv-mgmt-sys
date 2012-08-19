package com.vms.common;

public class BaseException extends Exception{
	
	private static final long serialVersionUID = 1L;

	/** error code is same with message */
	private String errorCode = null;

	/** Arguments of message */
	private Object[] args = null;
	
	protected String message = null;
	/**
	 * Default constructure
	 */
	public BaseException(){
		super();
	}
	
	/**
	 * Constructure
	 * @param errorCode
	 */
	public BaseException(String errorCode){
		super(errorCode);
		this.errorCode = errorCode;
	}
	/**
	 * Construct a instance using a given message.
	 * @param message specify an exception message.
	 */
	public BaseException(String errorCode, String message) {
		this.message = message;
		this.errorCode = errorCode;
	}
	/**
	 * Constructure
	 * @param errorCode
	 * @param args
	 */
	public BaseException(String errorCode , Object[] args){
		super(errorCode);
		this.errorCode = errorCode;
		this.args = args;
	}
	
	/**
	 * Construct a instance using a given message.
	 * @param message specify an exception message.
	 */
	public BaseException(String errorCode, String message, Object[] args) {
		this.message = message;
		this.errorCode = errorCode;
		this.args = args;
	}

	/**
	 * Constructure
	 * @param cause
	 */
	public BaseException(Throwable cause){
		super(cause);	
	}
	
	/**
	 * Constructure
	 * @param errorCode
	 * @param cause
	 */
	public BaseException(String errorCode, Throwable cause){
		super(errorCode , cause);
		this.errorCode = errorCode;
	}
	
	/**
	 * Constructure
	 * @param errorCode
	 * @param args
	 * @param cause
	 */
	public BaseException(String errorCode, Object[] args , Throwable cause){
		super(errorCode , cause);
		this.args = args;
	}	
	
	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
}