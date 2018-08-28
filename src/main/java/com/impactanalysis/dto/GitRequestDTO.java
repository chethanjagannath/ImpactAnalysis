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
	private String startCommitID;
	private String endCommitID;
	private LocalDate commitDate;
    
	public GitRequestDTO() {
	}
	
	public CommonUtility getCommonUtility() {
		return commonUtility;
	}

	public void setCommonUtility(CommonUtility commonUtility) {
		this.commonUtility = commonUtility;
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

	public String getStartCommitID() {
		return startCommitID;
	}

	public void setStartCommitID(String startCommitID) {
		this.startCommitID = startCommitID;
	}

	public String getEndCommitID() {
		return endCommitID;
	}

	public void setEndCommitID(String endCommitID) {
		this.endCommitID = endCommitID;
	}

	public LocalDate getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(LocalDate commitDate) {
		this.commitDate = commitDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commitDate == null) ? 0 : commitDate.hashCode());
		result = prime * result + ((endCommitID == null) ? 0 : endCommitID.hashCode());
		result = prime * result + ((repositoryOwnerId == null) ? 0 : repositoryOwnerId.hashCode());
		result = prime * result + ((repositoryName == null) ? 0 : repositoryName.hashCode());
		result = prime * result + ((startCommitID == null) ? 0 : startCommitID.hashCode());
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
		if (endCommitID == null) {
			if (other.endCommitID != null)
				return false;
		} else if (!endCommitID.equals(other.endCommitID))
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
		if (startCommitID == null) {
			if (other.startCommitID != null)
				return false;
		} else if (!startCommitID.equals(other.startCommitID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
