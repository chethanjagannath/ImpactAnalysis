package com.impactanalysis.dto;

import java.io.Serializable;
import java.util.List;

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

	private Integer apiId;
	private String apiName;
	private List<String> pathParams;
	private List<String> queryParams;
	private List<String> fileNames;
	private List<String> testSuites;
	
	public MapRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getApiId() {
		return apiId;
	}

	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}

	public String getApiName() {
		return apiName;
	}
	
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	
	public List<String> getPathParams() {
		return pathParams;
	}
	
	public void setPathParams(List<String> pathParams) {
		this.pathParams = pathParams;
	}
	
	public List<String> getQueryParams() {
		return queryParams;
	}
	
	public void setQueryParams(List<String> queryParams) {
		this.queryParams = queryParams;
	}
	
	public List<String> getFileNames() {
		return fileNames;
	}
	
	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}
	
	public List<String> getTestSuites() {
		return testSuites;
	}
	
	public void setTestSuites(List<String> testSuites) {
		this.testSuites = testSuites;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
