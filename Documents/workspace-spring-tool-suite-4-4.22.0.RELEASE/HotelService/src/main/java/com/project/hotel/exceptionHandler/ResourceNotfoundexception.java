package com.project.hotel.exceptionHandler;

public class ResourceNotfoundexception extends RuntimeException {

	public ResourceNotfoundexception() {
		super("Resource Not Found");
	}
	
	public ResourceNotfoundexception(String message) {
		super(message);
	}
	

}
