package com.impactanalysis.pojo;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.impactanalysis.utilities.CommonUtility;

public class Requestor implements Serializable{
	
	@Autowired
	private CommonUtility commonUtility;

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
	
	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
