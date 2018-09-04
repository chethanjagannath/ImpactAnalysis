package com.impactanalysis.pojo;

import java.io.Serializable;

public class Requestor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String emailId;
	
	public Requestor() {
		super();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
