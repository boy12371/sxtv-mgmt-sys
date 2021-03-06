package com.vms.beans;

import java.util.List;

import com.vms.common.JSONDataTableUtils;

public class JSONDataTable {

	private int recordsReturned;
	private int totalRecords=0;
	private int startIndex=0;
	private String sort;
	private String dir = JSONDataTableUtils.SORT_DIRECTION;
	private int pageSize = 3;
	private int rowsPerPage=10;

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

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	

}
