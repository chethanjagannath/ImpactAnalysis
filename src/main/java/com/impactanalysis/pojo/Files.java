package com.impactanalysis.pojo;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.impactanalysis.controllers.GitController;

public class Files implements Serializable{
	
	private static final Logger logger = LoggerFactory.getLogger(GitController.class);
	
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
	
/*	public String toString() {
		String jsonString = "";
        try {
            final ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(this);
        }
        catch (final IOException e) {
        	logger.error(String.format("Exception occured while converting object to json : %s ", jsonString));
        }
        return jsonString;
	}*/
}
