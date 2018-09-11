package com.impactanalysis.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.utilities.CommonUtility;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GitRequestDTO implements Serializable{
	
	@Autowired
	private CommonUtility commonUtility;

	private static final long serialVersionUID = 1L;
	
	private String repositoryOwnerId;
	private String repositoryName;
	private String branchName;
	private String startCommitId;
	private String endCommitId;
	private LocalDate commitDate;
    
	public GitRequestDTO() {
	}

	public String getRepositoryOwnerId() {
		return repositoryOwnerId;
	}

	public void setRepositoryOwnerId(String repositoryOwnerId) {
		this.repositoryOwnerId = repositoryOwnerId;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getStartCommitId() {
		return startCommitId;
	}

	public void setStartCommitId(String startCommitId) {
		this.startCommitId = startCommitId;
	}

	public String getEndCommitId() {
		return endCommitId;
	}

	public void setEndCommitId(String endCommitId) {
		this.endCommitId = endCommitId;
	}

	public LocalDate getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(LocalDate commitDate) {
		this.commitDate = commitDate;
	}

	public GitRequestDTO(String repositoryName, String repositoryOwnerId, String branchName) {
		super();
		this.repositoryName = repositoryName;
		this.repositoryOwnerId = repositoryOwnerId;
		this.branchName = branchName;
	}

	public GitRequestDTO(String repositoryName, String repositoryOwnerId, String branchName, String startCommitId,
			String endCommitId) {
		super();
		this.repositoryName = repositoryName;
		this.repositoryOwnerId = repositoryOwnerId;
		this.branchName = branchName;
		this.startCommitId = startCommitId;
		this.endCommitId = endCommitId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commitDate == null) ? 0 : commitDate.hashCode());
		result = prime * result + ((endCommitId == null) ? 0 : endCommitId.hashCode());
		result = prime * result + ((repositoryOwnerId == null) ? 0 : repositoryOwnerId.hashCode());
		result = prime * result + ((repositoryName == null) ? 0 : repositoryName.hashCode());
		result = prime * result + ((startCommitId == null) ? 0 : startCommitId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GitRequestDTO other = (GitRequestDTO) obj;
		if (commitDate == null) {
			if (other.commitDate != null)
				return false;
		} else if (!commitDate.equals(other.commitDate))
			return false;
		if (endCommitId == null) {
			if (other.endCommitId != null)
				return false;
		} else if (!endCommitId.equals(other.endCommitId))
			return false;
		if (repositoryOwnerId == null) {
			if (other.repositoryOwnerId != null)
				return false;
		} else if (!repositoryOwnerId.equals(other.repositoryOwnerId))
			return false;
		if (repositoryName == null) {
			if (other.repositoryName != null)
				return false;
		} else if (!repositoryName.equals(other.repositoryName))
			return false;
		if (startCommitId == null) {
			if (other.startCommitId != null)
				return false;
		} else if (!startCommitId.equals(other.startCommitId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
