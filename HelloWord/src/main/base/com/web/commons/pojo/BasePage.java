package com.web.commons.pojo;

public class BasePage {
	
	
	public int offset = 1;
	
	public int rows = 10;

	public int getOffset() {
		return (offset - 1) * rows;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	
}
