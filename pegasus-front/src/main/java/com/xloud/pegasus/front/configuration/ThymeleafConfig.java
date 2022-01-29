package com.xloud.pegasus.front.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

// https://itdogkuwaccho.hatenadiary.com/entry/2021/11/18/223954
@Configuration
public class ThymeleafConfig {

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

}