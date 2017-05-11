package com.web.commons.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTools {
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		ArrayList list = new ArrayList();
		Iterator it = jsonArr.iterator();

		while (it.hasNext()) {
			JSONObject json2 = (JSONObject) it.next();
			list.add(parseJSON2Map(json2.toString()));
		}

		return list;
	}

	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		HashMap map = new HashMap();
		JSONObject json = JSONObject.fromObject(jsonStr);
		Iterator arg3 = json.keySet().iterator();

		while (true) {
			while (arg3.hasNext()) {
				Object k = arg3.next();
				Object v = json.get(k);
				if (v instanceof JSONArray) {
					ArrayList list = new ArrayList();
					Iterator it = ((JSONArray) v).iterator();

					while (it.hasNext()) {
						JSONObject json2 = (JSONObject) it.next();
						list.add(parseJSON2Map(json2.toString()));
					}

					map.put(k.toString(), list);
				} else {
					map.put(k.toString(), v);
				}
			}

			return map;
		}
	}

	public static List<Map<String, Object>> getListByUrl(String url) {
		try {
			InputStream e = (new URL(url)).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(e));
			StringBuilder sb = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			return parseJSON2List(sb.toString());
		} catch (Exception arg4) {
			arg4.printStackTrace();
			return null;
		}
	}

	public static Map<String, Object> getMapByUrl(String url) {
		try {
			InputStream e = (new URL(url)).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(e));
			StringBuilder sb = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			return parseJSON2Map(sb.toString());
		} catch (Exception arg4) {
			arg4.printStackTrace();
			return null;
		}
	}
}