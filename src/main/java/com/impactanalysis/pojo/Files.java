package com.impactanalysis.pojo;

import java.io.Serializable;

public class Files implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String sha;
	private String status;
	private int additions;
	private int deletions;
	private int changes;

	public String getSHA() {
		return sha;
	}

	public void setSHA(String sHA) {
		sha = sHA;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAdditions() {
		return additions;
	}

	public void setAdditions(int additions) {
		this.additions = additions;
	}

	public int getDeletions() {
		return deletions;
	}

	public void setDeletions(int deletions) {
		this.deletions = deletions;
	}

	public int getChanges() {
		return changes;
	}

	public void setChanges(int changes) {
		this.changes = changes;
	}
}
