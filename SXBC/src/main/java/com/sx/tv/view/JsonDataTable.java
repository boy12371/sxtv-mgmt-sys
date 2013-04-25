package com.sx.tv.view;

import java.util.List;

public class JsonDataTable {

	private long page;
	private long total;
	private long size;
	private long records;

	private List<JsonData> rows;

	public JsonDataTable() {

	}

	public JsonDataTable(long page, long total, long size, long records, List<JsonData> rows) {
		super();
		this.page = page;
		this.total = total;
		this.size = size;
		this.records = records;
		this.rows = rows;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List<JsonData> getRows() {
		return rows;
	}

	public void setRows(List<JsonData> rows) {
		this.rows = rows;
	}


}
