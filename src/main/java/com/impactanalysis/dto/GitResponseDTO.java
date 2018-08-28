package com.impactanalysis.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.pojo.Files;
import com.impactanalysis.pojo.Item;
import com.impactanalysis.pojo.Stat;
import com.impactanalysis.utilities.CommonUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GitResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private CommonUtility commonUtility;
	
	private String sha;
	private String url;
	private String status;
	private Integer ahead_by;
	private Integer behind_by;
	private Integer total_commits;
	private Integer totalFiles;
	private ArrayList<Files> files;
	private Integer totalItems;
	private ArrayList<Item> items;
	private Stat stats;
    
	public GitResponseDTO() {	
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getAhead_by() {
		return ahead_by;
	}

	public void setAhead_by(Integer ahead_by) {
		this.ahead_by = ahead_by;
	}

	public Integer getBehind_by() {
		return behind_by;
	}

	public void setBehind_by(Integer behind_by) {
		this.behind_by = behind_by;
	}

	public Integer getTotal_commits() {
		return total_commits;
	}

	public void setTotal_commits(Integer total_commits) {
		this.total_commits = total_commits;
	}

	public Integer getTotalFiles() {
		return totalFiles;
	}

	public void setTotalFiles(Integer totalFiles) {
		this.totalFiles = totalFiles;
	}

	public ArrayList<Files> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<Files> files) {
		this.files = files;
	}

	public Integer getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Integer totalItems) {
		this.totalItems = totalItems;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public Stat getStats() {
		return stats;
	}

	public void setStats(Stat stats) {
		this.stats = stats;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
