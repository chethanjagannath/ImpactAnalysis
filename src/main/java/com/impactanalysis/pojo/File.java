package com.impactanalysis.pojo;

import java.io.Serializable;

public class File implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
