package com.impactanalysis.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.impactanalysis.utilities.CommonUtility;

@Entity
@Table(name="TBL_MAPPING")
public class MappingEntity {

	@Autowired
	private CommonUtility commonUtility;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="api_seq")
	@SequenceGenerator(name="api_seq", sequenceName="api_seq", allocationSize=1)
	@Column(name="apiId")
	private Integer apiId;
	
	@Lob
	@Column(name="apiName")
	private String apiName;
	
	@Lob
	@Column(name="pathParams")
	private String pathParams;
	
	@Lob
	@Column(name="queryParams")
	private String queryParams;
	
	@Lob
	@Column(name="fileNames")
	private String fileNames;
	
	@Lob
	@Column(name="testSuites")
	private String testSuites;

	public MappingEntity() {
		super();
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

	public String getPathParams() {
		return pathParams;
	}

	public void setPathParams(String pathParams) {
		this.pathParams = pathParams;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	public String getTestSuites() {
		return testSuites;
	}

	public void setTestSuites(String testSuites) {
		this.testSuites = testSuites;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}	
}
