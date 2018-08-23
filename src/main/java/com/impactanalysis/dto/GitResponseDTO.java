package com.impactanalysis.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.pojo.File;
import com.impactanalysis.pojo.Item;
import com.impactanalysis.pojo.Stat;
import com.impactanalysis.utilities.CommonUtility;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GitResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private CommonUtility comonUtility;
	
	private int total_count;
	private String sha;
	private String url;
	private String status;
	private int ahead_by;
	private int behind_by;
	private int total_commits;
	private ArrayList<File> files;
	private ArrayList<Item> items;
	private ArrayList<Stat> stats;
    
	public GitResponseDTO() {
		
	}
	
	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	public ArrayList<Stat> getStat() {
		return stats;
	}

	public void setStat(ArrayList<Stat> stat) {
		this.stats = stat;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
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

	public int getAhead_by() {
		return ahead_by;
	}

	public void setAhead_by(int ahead_by) {
		this.ahead_by = ahead_by;
	}

	public int getBehind_by() {
		return behind_by;
	}

	public void setBehind_by(int behind_by) {
		this.behind_by = behind_by;
	}

	public int getTotal_commits() {
		return total_commits;
	}

	public void setTotal_commits(int total_commits) {
		this.total_commits = total_commits;
	}

	public ArrayList<Stat> getStats() {
		return stats;
	}

	public void setStats(ArrayList<Stat> stats) {
		this.stats = stats;
	}
	
	@Override
	public String toString() {
		return comonUtility.toString();
	}
}
