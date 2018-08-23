package com.impactanalysis.pojo;

import java.io.Serializable;

public class Item implements Serializable{
	
	private String url;
	private String sha;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}
}
