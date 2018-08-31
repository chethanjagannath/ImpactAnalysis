package com.impactanalysis.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.utilities.CommonUtility;

@Entity
@Table(name="TBL_MAPPING")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class MappingEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	@Transient
	private CommonUtility commonUtility;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="api_seq1")
	@SequenceGenerator(name="api_seq1", sequenceName="api_seq1", allocationSize=1)
	@Column(name="apiId")
	private Integer apiId;
	
	@Lob
	@Column(name="apiName")
	private String apiName;
	
//	@ElementCollection
//	private HashSet<String> pathParams;
	
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
	@Column(name="testSuiteNames")
	private String testSuiteNames;

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

	public String getTestSuiteNames() {
		return testSuiteNames;
	}

	public void setTestSuiteNames(String testSuiteNames) {
		this.testSuiteNames = testSuiteNames;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}	
}
