package com.impactanalysis.pojo;

import java.io.Serializable;

public class Commit implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Committer committer;

	public Committer getCommitter() {
		return committer;
	}

	public void setCommitter(Committer committer) {
		this.committer = committer;
	}
}
