package com.example.innotek.demobankchallenge.model.server;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
