package com.xloud.pegasus.service;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@EnableFeignClients({ "com.xloud.pegasus.common", "com.xloud.pegasus.service" })
@ComponentScan({ "com.xloud.pegasus.common", "com.xloud.pegasus.service" })
@MapperScan("com.xloud.pegasus.common.domain.repository")
public class PegasusServiceApplication {

	public static void main(String[] args) throws Exception {
		Properties properties = System.getProperties();
		properties.put("spring.config.location", "classpath:application.yml,classpath:/application-override.yml");
		SpringApplication.run(PegasusServiceApplication.class, args);
	}

}
