package com.impactanalysis.response;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.impactanalysis.pojo.File;
import com.impactanalysis.pojo.Item;
import com.impactanalysis.pojo.Stat;
@JsonIgnoreProperties(ignoreUnknown = true)
public class IAResponse implements Serializable{
	private ArrayList<File> files;
	private ArrayList<Item> items;
	private ArrayList<Stat> stat;

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
		return stat;
	}

	public void setStat(ArrayList<Stat> stat) {
		this.stat = stat;
	}
}
