package com.boot.dto.response;

public class ResponseDto {
	private String success;
	private Integer statusCode;
	private String message;
	
	public ResponseDto() {}

	public ResponseDto(String success, Integer statusCode, String message) {
		this.success = success;
		this.statusCode = statusCode;
		this.message = message;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer errorCode) {
		this.statusCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	

}
