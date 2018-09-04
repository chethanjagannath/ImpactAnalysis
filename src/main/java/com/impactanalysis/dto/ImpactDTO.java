package com.impactanalysis.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.impactanalysis.utilities.CommonUtility;

public class ImpactDTO implements Serializable {

	@Autowired
	private CommonUtility commonUtility;

	private static final long serialVersionUID = 1L;

	private Set<String> testSuiteList;
	private Map<String, Set<String>> apiTestSuitesMappingList;

	public ImpactDTO() {
		super();
	}

	public Set<String> getTestSuiteList() {
		return testSuiteList;
	}

	public void setTestSuiteList(Set<String> testSuiteList) {
		this.testSuiteList = testSuiteList;
	}

	public Map<String, Set<String>> getApiTestSuitesMappingList() {
		return apiTestSuitesMappingList;
	}

	public void setApiTestSuitesMappingList(Map<String, Set<String>> apiTestSuitesMappingList) {
		this.apiTestSuitesMappingList = apiTestSuitesMappingList;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
