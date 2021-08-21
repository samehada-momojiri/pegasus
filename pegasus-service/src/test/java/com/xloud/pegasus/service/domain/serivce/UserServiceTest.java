package com.xloud.pegasus.service.domain.serivce;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.xloud.pegasus.service.domain.service.UserService;
import com.xloud.pegasus.service.domain.service.dto.UserDto;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void test001() {
		UserDto userDto = userService.findById(1L);
		Assertions.assertNotNull(userDto);
		Assertions.assertEquals("Jack", userDto.getUserName());
	}
}
