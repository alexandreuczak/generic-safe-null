package br.com.safenull.exception;

public class SafeNullException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SafeNullException(String message, Throwable throwable){
		super(message, throwable);
	}
	
}
