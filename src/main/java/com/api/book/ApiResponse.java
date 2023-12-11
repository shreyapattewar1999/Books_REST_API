package com.api.book;

public class ApiResponse {

	private String message;
	private Boolean success;

	public ApiResponse() {
		// TODO Auto-generated constructor stub
	}

	public ApiResponse(String message, Boolean isSuccess) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.success = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
