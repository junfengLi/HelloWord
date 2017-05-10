package com.web.commons.jqgrid;

import java.util.ArrayList;
import java.util.List;

public class UIPage {
	private String PAGE_SIZE = "10";
	private long total;
	private List rows = new ArrayList();
	private long records;
	
	public UIPage() {
		super();
	}
	public UIPage(String pageSize) {
		super();
		this.PAGE_SIZE = pageSize;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
		if (records%Integer.valueOf(this.PAGE_SIZE) == 0) {
			this.total = records/Integer.valueOf(this.PAGE_SIZE);
		} else {
			this.total = records/Integer.valueOf(this.PAGE_SIZE) + 1;
		}
	}

	public List getRows() {
		return this.rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return this.total;
	}

}