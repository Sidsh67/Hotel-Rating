package com.project.userservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resource Not Found On Server");
		// TODO Auto-generated constructor stub
	}
	
	public ResourceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
