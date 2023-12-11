package com.api.book.response;

import org.h2.api.ErrorCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {

	private boolean status;
	private String message;
	private Object data;
	private ErrorCode errorCode;

	// use constructors instead of setters

	// getters
	public ApiResponse() {
		// TODO Auto-generated constructor stub
	}

}