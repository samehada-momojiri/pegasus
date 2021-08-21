package com.xloud.pegasus.service.test;

import java.util.Properties;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestApplicationConfigLoader implements BeforeAllCallback {

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		// https://int128.hatenablog.com/entry/2018/01/31/130126
		Properties properties = System.getProperties();
		properties.put("spring.config.location", "classpath:application.yml,classpath:/application-override.yml,classpath:/application-test.yml");
	}

}
