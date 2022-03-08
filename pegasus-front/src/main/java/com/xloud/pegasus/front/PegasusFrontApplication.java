package com.xloud.pegasus.front;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 一時的にSpringSecurityによる認証を無効化
// https://penguinlabo.hatenablog.com/entry/springsecuritymukouka
// @SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@SpringBootApplication()
@EnableFeignClients
public class PegasusFrontApplication {

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		properties.put("spring.config.location",
				"classpath:bootstrap.yml,classpath:application.yml,classpath:/application-override.yml");
		SpringApplication.run(PegasusFrontApplication.class, args);
	}

}
