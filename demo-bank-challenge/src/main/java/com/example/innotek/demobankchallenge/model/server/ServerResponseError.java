package com.example.innotek.demobankchallenge.model.server;

import java.util.ArrayList;
import java.util.List;

public class ServerResponseError  {

	
	private  String requestID  ;
	private  String descritption;
	private  String detail;
	
	private  List<ServerErrorResponse> errors = new ArrayList<>();

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getDescritption() {
		return descritption;
	}

	public void setDescritption(String descritption) {
		this.descritption = descritption;
	}

	public List<ServerErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<ServerErrorResponse> errors) {
		this.errors = errors;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	

	
	
	
	
	



	
}
