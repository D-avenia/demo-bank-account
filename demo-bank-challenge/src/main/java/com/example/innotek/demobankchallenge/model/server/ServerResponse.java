package com.example.innotek.demobankchallenge.model.server;


import java.util.ArrayList;
import java.util.List;

public class ServerResponse<T> {

	
	private String requestID  ;
	private String status;
	private List<ServerErrorResponse> errors = new ArrayList<>();
	private T payload;

	
	public static <TPayload> ServerResponse<TPayload> OK ( TPayload payload,  String requestID ) {
		ServerResponse<TPayload> response = new ServerResponse<>();
		
		response.setStatus("OK");
		response.setRequestID(requestID);
		response.setPayload(payload);
		return response;
	}
	
	public ServerResponse() {
		
		
	}

	public ServerResponse(String status, T payload) {
		super();
		this.status = status;
		this.payload = payload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public List<ServerErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(final List<ServerErrorResponse> errors) {
		this.errors = errors;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(final T payload) {
		this.payload = payload;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	
}
