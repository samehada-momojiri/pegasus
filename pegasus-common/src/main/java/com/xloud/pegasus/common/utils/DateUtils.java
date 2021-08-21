package com.xloud.pegasus.common.utils;

import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils {

	private static final String THREAD_LOCAL_KEY = "___THREAD_LOCAL_DATE___";

	// ISO8601拡張形式
	private static final Pattern DEFAULT_DATE_TIME_FORMAT_PATTERN = Pattern
			.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\+[0-9]{2}:[0-9]{2}");

	private DateUtils() {
	}

	public static void setThreadDateTime(final Date datetime) {
		ThreadLocalUtils.set(THREAD_LOCAL_KEY, datetime);
	}

	public static Date getThreadDateTime() {
		return ThreadLocalUtils.get(THREAD_LOCAL_KEY, Date.class);
	}

	public static String format(Date date) {
		if (date == null) {
			return null;
		}
		return DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.format(date);
	}

	public static Date parse(String strDate) {
		try {
			return DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isDefaultDateTimeFormat(String str) {
		if (str == null) {
			return false;
		}
		return DEFAULT_DATE_TIME_FORMAT_PATTERN.matcher(str).matches();
	}

}