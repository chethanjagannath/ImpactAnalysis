package com.impactanalysis.dto;

import java.io.Serializable;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.utilities.CommonUtility;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class MapRequestDTO implements Serializable{
	
	@Autowired
	private CommonUtility commonUtility;
	
	private static final long serialVersionUID = 1L;

	private String apiId;
	private String apiName;
	private HashSet<String> pathParams;
	private HashSet<String> queryParams;
	private HashSet<String> fileNames;
	private HashSet<String> testSuiteNames;
	
	public MapRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public HashSet<String> getPathParams() {
		return pathParams;
	}

	public void setPathParams(HashSet<String> pathParams) {
		this.pathParams = pathParams;
	}

	public HashSet<String> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(HashSet<String> queryParams) {
		this.queryParams = queryParams;
	}

	public HashSet<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(HashSet<String> fileNames) {
		this.fileNames = fileNames;
	}

	public HashSet<String> getTestSuiteNames() {
		return testSuiteNames;
	}

	public void setTestSuiteNames(HashSet<String> testSuiteNames) {
		this.testSuiteNames = testSuiteNames;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
