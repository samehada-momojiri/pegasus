package com.xloud.pegasus.service.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xloud.pegasus.common.web.request.UserCreateRequest;
import com.xloud.pegasus.common.web.request.UserUpdateRequest;
import com.xloud.pegasus.common.web.response.UserCreateResponse;
import com.xloud.pegasus.common.web.response.UserGetListResponse;
import com.xloud.pegasus.common.web.response.UserGetResponse;
import com.xloud.pegasus.service.domain.service.UserService;
import com.xloud.pegasus.service.domain.service.dto.UserDto;
import com.xloud.pegasus.service.web.helper.UsersHelper;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping(value = "/users", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public UserGetListResponse getList() {
		List<UserDto> resultList = userService.findAll();
		return UsersHelper.convertToUsersGetListResponse(resultList);
	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public UserGetResponse get(@PathVariable("id") Long id) {
		LOGGER.info("#id : {}", id);
		UserDto result = userService.findById(id);
		return UsersHelper.convertToUsersGetResponse(result);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserCreateResponse create(@Validated @RequestBody UserCreateRequest request) {
		LOGGER.info("#UsersCreateRequest : {}", request);
		UserDto userDto = UsersHelper.convertFromUsersCreateRequest(request);
		UserDto result = userService.create(userDto);
		return UsersHelper.convertToUsersCreateResponse(result);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable("id") Long id, @Validated @RequestBody UserUpdateRequest request) {
		LOGGER.info("#id : {}", id);
		LOGGER.info("#UsersUpdateRequest : {}", request);
		UserDto userDto = UsersHelper.convertFromUsersUpdateRequest(id, request);
		userService.update(userDto);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		LOGGER.info("#id : {}", id);
		userService.delete(id);
	}

}