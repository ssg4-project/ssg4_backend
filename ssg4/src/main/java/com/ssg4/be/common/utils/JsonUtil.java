package com.ssg4.be.common.utils;

import com.google.gson.Gson;

public class JsonUtil {
	/**
	 * pojo => json
	 */
	public static String getJsonFromPojo(Object object) {
		Gson gson = new Gson();
		return gson.toJson(object);
	}

	/**
	 * json => pojo
	 */
	public static <T> T getPojoFromJson(String json, Class<T> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}
}
