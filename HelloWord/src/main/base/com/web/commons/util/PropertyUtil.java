package com.web.commons.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class PropertyUtil {
	private String propertyFilePath = "application";
	private ResourceBundle bundle;

	public PropertyUtil(String propertyFilePath) {
		this.propertyFilePath = propertyFilePath;
		this.bundle = ResourceBundle.getBundle(propertyFilePath);
	}

	public PropertyUtil() {
		this.bundle = ResourceBundle.getBundle(this.propertyFilePath);
	}

	public static PropertyUtil getInstance() {
		return new PropertyUtil();
	}

	public String getValue(String key) {
		return this.bundle.getString(key);
	}

	public List<String> getValues(String key) {
		ArrayList list = new ArrayList();
		String[] values = this.bundle.getStringArray(key);
		String[] arg6 = values;
		int arg5 = values.length;

		for (int arg4 = 0; arg4 < arg5; ++arg4) {
			String value = arg6[arg4];
			list.add(value);
		}

		return list;
	}

	public List<String> getKeys() {
		ArrayList list = new ArrayList();
		Enumeration ks = this.bundle.getKeys();

		while (ks.hasMoreElements()) {
			String key = (String) ks.nextElement();
			list.add(key);
		}

		return list;
	}

	public boolean contains(String key) {
		boolean result = false;
		Iterator arg3 = this.getKeys().iterator();

		while (arg3.hasNext()) {
			String k = (String) arg3.next();
			if (k.equals(key)) {
				result = true;
				break;
			}
		}

		return result;
	}

	public String getPropertyFilePath() {
		return this.propertyFilePath;
	}

	public void setPropertyFilePath(String propertyFilePath) {
		this.propertyFilePath = propertyFilePath;
	}
}