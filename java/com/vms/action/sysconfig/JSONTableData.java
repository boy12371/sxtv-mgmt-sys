package com.vms.action.sysconfig;

import java.util.List;

public class JSONTableData {

	private int recordsReturned=10;
	private int totalRecords=16;
	private int startIndex=0;
	private String sort;
	private String dir = "asc";
	private int pageSize = 5;

	private List records;

	public int getRecordsReturned() {
		return recordsReturned;
	}

	public void setRecordsReturned(int recordsReturned) {
		this.recordsReturned = recordsReturned;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getRecords() {
		return records;
	}

	public void setRecords(List records) {
		this.records = records;
	}

}
