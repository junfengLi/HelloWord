//package com.web.commons.util;
//
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import java.lang.reflect.Type;
//
//public class JsonUtil {
//	public static String convertToJosn(Object object) {
//		Gson gson = (new GsonBuilder()).create();
//		return gson.toJson(object);
//	}
//
//	public static String convertToJosnWithQuotes(Object object) {
//		Gson gson = (new GsonBuilder()).create();
//		String json = gson.toJson(object);
//		return json.replaceAll("\"", "\\\\\"");
//	}
//
//	public static Object convertToJosn(String str, Type type) {
//		Gson gson = (new GsonBuilder()).create();
//		return gson.fromJson(str, type);
//	}
//}
