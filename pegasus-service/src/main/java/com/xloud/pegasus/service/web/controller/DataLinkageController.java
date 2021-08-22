package com.xloud.pegasus.service.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xloud.pegasus.common.bl.DataLinkageBL;
import com.xloud.pegasus.common.exception.BusinessException;
import com.xloud.pegasus.common.web.response.UserGetResponse;
import com.xloud.pegasus.service.domain.service.UserService;
import com.xloud.pegasus.service.domain.service.dto.UserDto;
import com.xloud.pegasus.service.web.helper.UsersHelper;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping(value = "/datalinkage/users/", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class DataLinkageController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DataLinkageController.class);

	private final UserService userService;

	private final DataLinkageBL dataLinkageBL;

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public UserGetResponse get(@PathVariable("id") Long id) {
		LOGGER.info("#id : {}", id);
		UserDto result = userService.findById(id);

		try {
			dataLinkageBL.publish("AA", "UserInfo", null, result);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		return UsersHelper.convertToUsersGetResponse(result);
	}

}