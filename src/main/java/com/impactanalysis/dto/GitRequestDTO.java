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
	private CommonUtility comonUtility;

	private static final long serialVersionUID = 1L;
	
	private String ownerId;
	private String projectRepo;
	private String startCommitID;
	private String endCommitID;
	private LocalDate commitDate;
    
	public GitRequestDTO() {
	}
	
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getProjectRepo() {
		return projectRepo;
	}

	public void setProjectRepo(String projectRepo) {
		this.projectRepo = projectRepo;
	}

	public LocalDate getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(LocalDate commitDate) {
		this.commitDate = commitDate;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commitDate == null) ? 0 : commitDate.hashCode());
		result = prime * result + ((endCommitID == null) ? 0 : endCommitID.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((projectRepo == null) ? 0 : projectRepo.hashCode());
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
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (projectRepo == null) {
			if (other.projectRepo != null)
				return false;
		} else if (!projectRepo.equals(other.projectRepo))
			return false;
		if (startCommitID == null) {
			if (other.startCommitID != null)
				return false;
		} else if (!startCommitID.equals(other.startCommitID))
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "GitUserDTO [ownerId=" + ownerId + ", projectRepo=" + projectRepo + ", commitDate=" + commitDate
//				+ ", startCommitID=" + startCommitID + ", endCommitID=" + endCommitID + "]";
//	}
//	
	@Override
	public String toString() {
		return comonUtility.toString();
	}

}
