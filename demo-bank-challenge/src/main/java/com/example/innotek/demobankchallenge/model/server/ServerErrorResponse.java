package com.example.innotek.demobankchallenge.model.server;

public class ServerErrorResponse {
	private String code;
	private String description;
	private String params;
	private String details;

	
	public ServerErrorResponse() {
		
	}
	public ServerErrorResponse(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	
	}
	
	
	public ServerErrorResponse(String code, String description, String params, String details) {
		super();
		this.code = code;
		this.description = description;
		this.params = params;
		this.details = details;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
