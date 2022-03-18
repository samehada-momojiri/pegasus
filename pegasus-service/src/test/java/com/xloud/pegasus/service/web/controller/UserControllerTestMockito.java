package com.xloud.pegasus.service.web.controller;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xloud.pegasus.common.web.response.UserGetResponse;
import com.xloud.pegasus.service.common.context.ThreadContext;
import com.xloud.pegasus.service.domain.service.UserService;
import com.xloud.pegasus.service.domain.service.dto.UserDto;
import com.xloud.pegasus.service.test.TestApplicationConfigLoader;

@SpringBootTest
@ExtendWith({ SpringExtension.class, TestApplicationConfigLoader.class })
public class UserControllerTestMockito {

	// https://dawaan.com/mockbean-vs-mock/
	@MockBean
	private UserService userService;

	@Autowired
	private UserController userController;

	@Autowired
	private MockHttpServletRequest request;

	@Autowired
	private ThreadContext threadContext;

	@BeforeEach
	public void setup() {
		threadContext.setSessionId("1234-5678-9012-3456");
		threadContext.setThreadDate(new Date());
	}

	@Test
	public void test001() {
		{
			UserDto mock = new UserDto();
			mock.setUserId(1L);
			mock.setUserName("Kevin");
			Mockito.when(userService.findById(1L)).thenReturn(mock);
		}
		{
			UserDto mock = new UserDto();
			mock.setUserId(2L);
			mock.setUserName("Damian");
			Mockito.when(userService.findById(2L)).thenReturn(mock);
		}
		UserGetResponse userGetResponse = userController.get(2L, request);
		Assertions.assertNotNull(userGetResponse);
		Assertions.assertEquals("Damian", userGetResponse.getUserName());
	}

	@Test
	public void test002() {
		{
			UserDto mock = new UserDto();
			mock.setUserId(1L);
			mock.setUserName("Kevin");
			Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(mock);
		}
		UserGetResponse userGetResponse = userController.get(2L, request);
		Assertions.assertNotNull(userGetResponse);
		Assertions.assertEquals("Kevin", userGetResponse.getUserName());
	}
}
