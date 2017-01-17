package com.mtime.wordbank.exception;

public class BussinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String description;

	public BussinessException() {
		super();
	}

	public BussinessException(String message) {
		super(message);
	}

	public BussinessException(String message, String description) {
		super(message);
		this.description = description;
	}

	public BussinessException(String message, String description, Throwable cause) {
		super(message, cause);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
