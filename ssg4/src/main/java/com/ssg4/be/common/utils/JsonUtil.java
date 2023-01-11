package com.ssg4.be.common.utils;

import com.google.gson.Gson;

public class JsonUtil {
	/**
	 * pojo => json
	 */
	public static String pojoToJson(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	/**
	 * json => pojo
	 */
	public static Object jsonToPojo(String json, Object object) {
		Gson gson = new Gson();
		return gson.fromJson(json, object.getClass());
	}
}
