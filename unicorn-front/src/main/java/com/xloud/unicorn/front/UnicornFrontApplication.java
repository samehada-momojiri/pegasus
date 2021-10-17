package com.xloud.unicorn.front;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnicornFrontApplication {

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		properties.put("spring.config.location", "classpath:application.yml,classpath:/application-override.yml");
		SpringApplication.run(UnicornFrontApplication.class, args);
	}

}
