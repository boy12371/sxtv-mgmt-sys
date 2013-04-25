package com.sx.tv.view;

public class JsonData {
	private long id;

	private Object[] cell;
	
	public long getId() {
		return id;
	}

	
	public JsonData(long id, Object[] cell) {
		super();
		this.id = id;
		this.cell = cell;
	}


	public Object[] getCell() {
		return cell;
	}


	public void setCell(Object[] cell) {
		this.cell = cell;
	}


	public void setId(long id) {
		this.id = id;
	}



	

	
}
