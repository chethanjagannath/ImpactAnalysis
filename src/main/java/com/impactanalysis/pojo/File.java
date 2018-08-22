package com.impactanalysis.pojo;

public class File {
	private String SHA;
	private String status;
	private int additions;
	private int deletions;
	private int changes;

	public String getSHA() {
		return SHA;
	}

	public void setSHA(String sHA) {
		SHA = sHA;
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
