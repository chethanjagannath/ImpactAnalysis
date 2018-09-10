package com.impactanalysis.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.utilities.CommonUtility;

@Entity
@Table(name = "TBL_DEPLOYMENT")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class DeploymentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	@Transient
	private CommonUtility commonUtility;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deployment_seq")
	@SequenceGenerator(name = "deployment_seq", sequenceName = "deployment_seq", allocationSize = 1)
	@Column(name = "deploymentId")
	private Long deploymentId;
	
	@Column(name = "repositoryName")
	private String repositoryName;
	
	@Column(name = "repositoryOwnerId")
	private String repositoryOwnerId;
	
	@Column(name = "createdDate")
	private LocalDateTime createdDate = LocalDateTime.now();
	
	@Column(name = "branchName")
	private String branchName;
	
	@Column(name = "commitId")
	private String commitId;

	public DeploymentEntity() {
		super();
	}

	public Long getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(Long deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public String getRepositoryOwnerId() {
		return repositoryOwnerId;
	}

	public void setRepositoryOwnerId(String repositoryOwnerId) {
		this.repositoryOwnerId = repositoryOwnerId;
	}

	public String getCreatedDate() {
		return createdDate.toString();
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCommitId() {
		return commitId;
	}

	public void setCommitId(String commitId) {
		this.commitId = commitId;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
