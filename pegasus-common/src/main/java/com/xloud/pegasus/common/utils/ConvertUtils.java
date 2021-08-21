package com.xloud.pegasus.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateTimeConverter;

public class ConvertUtils {
	private static final BeanUtilsBean BUB;
	static {
		BUB = new BeanUtilsBean(new ConvertUtilsBean(), BeanUtilsBean.getInstance().getPropertyUtils());
		BUB.getConvertUtils().register(new MyStringConverter(), String.class);
		final MyDateConverter myDateConverter = new MyDateConverter();
		myDateConverter.setPatterns(new String[] { "yyyyMMdd", "yyyyMMddHHmmss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
				"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss" });
		BUB.getConvertUtils().register(myDateConverter, Date.class);
	}

	static class MyStringConverter implements Converter {
		@Override
		public <T> T convert(Class<T> type, Object value) {
			if (value == null) {
				return null;
			} else {
				if (value instanceof java.util.Date) {
					return type.cast(DateUtils.format((Date) value));
				}
				return type.cast(value.toString());
			}
		}
	}

	static class MyDateConverter extends DateTimeConverter {
		public MyDateConverter() {
			super();
		}

		public MyDateConverter(Object defaultValue) {
			super(defaultValue);
		}

		@Override
		protected Class<?> getDefaultType() {
			return Date.class;
		}

		@Override
		public <T> T convert(Class<T> type, Object value) {
			if (value != null && value instanceof java.lang.String
					&& DateUtils.isDefaultDateTimeFormat((String) value)) {
				return type.cast(DateUtils.parse((String) value));
			} else {
				return super.convert(type, value);
			}
		}
	}

	public static void copyProperties(Object src, Object dest) {
		try {
			BUB.copyProperties(dest, src);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new IllegalStateException(e);
		}
	}
}
