package com.impactanalysis.dto;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.entities.DeploymentEntity;
import com.impactanalysis.pojo.Requestor;
import com.impactanalysis.utilities.CommonUtility;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DeploymentRequestDTO implements Serializable{
	
	@Autowired
	private CommonUtility commonUtility;
	
	private static final long serialVersionUID = 1L;

	private Requestor requestor;
	private DeploymentEntity deploymentEntity;
	
	public DeploymentRequestDTO() {
		super();
	}
	
	public Requestor getRequestor() {
		return requestor;
	}

	public void setRequestor(Requestor requestor) {
		this.requestor = requestor;
	}

	public DeploymentEntity getDeploymentEntity() {
		return deploymentEntity;
	}

	public void setDeploymentEntity(DeploymentEntity deploymentEntity) {
		this.deploymentEntity = deploymentEntity;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
