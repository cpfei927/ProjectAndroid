package com.cpfei.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

	public static String bean2json(Object bean) {
		Gson gson = new GsonBuilder().setDateFormat(TimeUtils.DATE_TIME_FMT1).create();
		return gson.toJson(bean);
	}

	@SuppressWarnings("unchecked")
	public static <T> T json2bean(String json, Type type) {
		try {
			Gson gson = new GsonBuilder().setDateFormat(TimeUtils.DATE_TIME_FMT1).create();
			return (T) gson.fromJson(json, type);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getJsonValue(String jsonText, String jsonKey) {
		JsonElement jsonElement = new JsonParser().parse(jsonText);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		return jsonObject.get(jsonKey).getAsString();
	}

	public static <T> List<T> arr2List(T[] arr) {

		List<T> lists = new ArrayList<>();
		if (arr != null && arr.length > 0) {
			for (T s : arr) {
				lists.add(s);
			}
		}
		return lists;
	}

	public static <T> List<T> copy(List<T> arr) {
		List<T> lists = new ArrayList<>();
		if (arr != null && arr.size() > 0) {
			for (T s : arr) {
				lists.add(s);
			}
		}
		return lists;
	}

}
