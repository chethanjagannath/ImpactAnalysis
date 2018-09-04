package com.impactanalysis.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.utilities.CommonUtility;

@Entity
@Table(name = "TBL_MAPPING")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class MappingEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	@Transient
	private CommonUtility commonUtility;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_seq")
	@SequenceGenerator(name = "api_seq", sequenceName = "api_seq", allocationSize = 1)
	@Column(name = "apiId")
	private Integer apiId;

	@Lob
	@NotNull
	@Column(name = "apiName")
	private String apiName;

	@Lob
	@Column(name = "pathParams")
	private String pathParams;

	@Lob
	@Column(name = "queryParams")
	private String queryParams;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "files")
	@CollectionTable(name = "TBL_MAPPING_FILES", joinColumns = { @JoinColumn(name = "apiId") })
	private Set<String> fileNames = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "testsuites")
	@CollectionTable(name = "TBL_MAPPING_TESTSUITES", joinColumns = { @JoinColumn(name = "apiId") })
	private Set<String> testSuiteNames = new HashSet<>();

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

	public Set<String> getFileNames() {
		return fileNames;
	}

	public void setFileNames(Set<String> fileNames) {
		this.fileNames = fileNames;
	}

	public Set<String> getTestSuiteNames() {
		return testSuiteNames;
	}

	public void setTestSuiteNames(Set<String> testSuiteNames) {
		this.testSuiteNames = testSuiteNames;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
