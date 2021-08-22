package com.xloud.pegasus.datalinkage;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@EnableFeignClients
@SpringBootApplication
//@ComponentScans({ @ComponentScan("com.xloud.pegasus.common"), @ComponentScan("com.xloud.pegasus.datalinkage") })
@ComponentScan("com.xloud.pegasus")
public class PegasusDatalinkageApplication {

	public static void main(String[] args) {
		Properties properties = System.getProperties();
		properties.put("spring.config.location", "classpath:application.yml,classpath:/application-override.yml");
		SpringApplication.run(PegasusDatalinkageApplication.class, args);
	}

}
