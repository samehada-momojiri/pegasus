package com.xloud.pegasus.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class ApplicationListner {

	@PostConstruct
	public void init() {
		System.out.println("################################################################");
		System.out.println("# アプリケーションが起動しました");
		System.out.println("################################################################");
	}

	@PreDestroy
	public void destoroy() {
		System.out.println("################################################################");
		System.out.println("# アプリケーションが終了します");
		System.out.println("################################################################");
	}

}
