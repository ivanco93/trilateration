package com.icastiblanco.trilateration.model;

public class RequestResponse {
	private String message;
	public RequestResponse() {};
	public RequestResponse(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String toJsonString() {
		return "{\"message\":\""+message+"\"}";
	}
	@Override
	public String toString() {
		return "RequestResponse [message=" + message + "]";
	}
}
