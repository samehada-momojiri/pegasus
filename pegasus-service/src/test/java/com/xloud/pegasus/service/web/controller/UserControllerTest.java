package com.xloud.pegasus.service.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.xloud.pegasus.common.web.response.UserGetResponse;
import com.xloud.pegasus.service.test.TestApplicationConfigLoader;

// https://terasolunaorg.github.io/guideline/5.4.1.RELEASE/ja/UnitTest/ImplementsOfUnitTest/UsageOfLibraryForTest.html
@ExtendWith({ SpringExtension.class, TestApplicationConfigLoader.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextBeforeModesTestExecutionListener.class, TransactionalTestExecutionListener.class })
//@EnableAutoConfiguration
@SpringBootTest
public class UserControllerTest {

	// TODO 原因がわからないがこのオブジェクトがDIされないためJUnitが動作しない
	@Autowired
	private UserControllerTestFeignClient client;

	@Test
	public void test001() {
		ResponseEntity<UserGetResponse> responseEntity = client.getUser(1L);
		UserGetResponse userGetResponse = responseEntity.getBody();
		Assertions.assertNotNull(userGetResponse);
		Assertions.assertEquals("Jack", userGetResponse.getUserName());
	}
}
