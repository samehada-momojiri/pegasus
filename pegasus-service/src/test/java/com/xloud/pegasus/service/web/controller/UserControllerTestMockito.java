package com.xloud.pegasus.service.web.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xloud.pegasus.service.domain.service.UserService;
import com.xloud.pegasus.service.domain.service.dto.UserDto;
import com.xloud.pegasus.service.test.TestApplicationConfigLoader;
import com.xloud.pegasus.service.web.dto.response.UserGetResponse;

@SpringBootTest
@ExtendWith({ SpringExtension.class, TestApplicationConfigLoader.class })
public class UserControllerTestMockito {

	// https://dawaan.com/mockbean-vs-mock/
	@MockBean
	private UserService userService;

	@Autowired
	private UserController userController;

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
		UserGetResponse userGetResponse = userController.get(2L);
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
		UserGetResponse userGetResponse = userController.get(2L);
		Assertions.assertNotNull(userGetResponse);
		Assertions.assertEquals("Kevin", userGetResponse.getUserName());
	}
}
