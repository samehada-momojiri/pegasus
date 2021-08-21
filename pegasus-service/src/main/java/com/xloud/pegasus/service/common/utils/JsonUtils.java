package com.xloud.pegasus.service.common.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.xloud.pegasus.service.exception.SystemException;

public class JsonUtils {

	private static final ObjectMapper JSON_READ_CONVERTOR = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private static final ObjectMapper JSON_WRITE_CONVERTOR = new ObjectMapper();

	public static String convertToJson(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return JSON_WRITE_CONVERTOR.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new SystemException("Object→JSONの変換に失敗しました", e);
		}
	}

	public static <T> T convertFromJson(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		try {
			return JSON_READ_CONVERTOR.readValue(json, clazz);
		} catch (JsonProcessingException e) {
			throw new SystemException("JSON→Objectの変換に失敗しました", e);
		}
	}

	public static <T> T convertListFromJson(String json, Class<T> clazz) {
		if (json == null) {
			return null;
		}
		try {
			CollectionType type = JSON_READ_CONVERTOR.getTypeFactory().constructCollectionType(List.class, clazz);
			return JSON_READ_CONVERTOR.readValue(json, type);
		} catch (JsonProcessingException e) {
			throw new SystemException("JSON→Objectの変換に失敗しました", e);
		}
	}
}
