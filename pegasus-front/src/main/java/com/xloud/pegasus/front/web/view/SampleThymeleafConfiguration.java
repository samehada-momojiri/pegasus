package com.xloud.pegasus.front.web.view;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleThymeleafConfiguration {
	@Bean
	public SampleDialect getSampleDialect() {
		return new SampleDialect();
	}
}
