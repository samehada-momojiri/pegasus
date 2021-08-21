package com.xloud.pegasus.service.web.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.xloud.pegasus.service.common.utils.ConvertUtils;
import com.xloud.pegasus.service.domain.service.dto.UserDto;
import com.xloud.pegasus.service.web.dto.request.UserCreateRequest;
import com.xloud.pegasus.service.web.dto.request.UserUpdateRequest;
import com.xloud.pegasus.service.web.dto.response.UserCreateResponse;
import com.xloud.pegasus.service.web.dto.response.UserGetListResponse;
import com.xloud.pegasus.service.web.dto.response.UserGetListResponse.User;
import com.xloud.pegasus.service.web.dto.response.UserGetResponse;

public class UsersHelper {
	public static UserGetListResponse convertToUsersGetListResponse(List<UserDto> list) {
		final UserGetListResponse response = new UserGetListResponse();
		List<User> users = list.stream().map((dto) -> {
			final UserGetListResponse.User user = new UserGetListResponse.User();
			ConvertUtils.copyProperties(dto, user);
			return user;
		}).collect(Collectors.toList());
		response.setUsers(users);
		return response;
	}

	public static UserGetResponse convertToUsersGetResponse(UserDto dto) {
		final UserGetResponse response = new UserGetResponse();
		ConvertUtils.copyProperties(dto, response);
		return response;
	}

	public static UserDto convertFromUsersCreateRequest(UserCreateRequest request) {
		final UserDto userDto = new UserDto();
		ConvertUtils.copyProperties(request, userDto);
		return userDto;
	}

	public static UserCreateResponse convertToUsersCreateResponse(UserDto dto) {
		final UserCreateResponse response = new UserCreateResponse();
		ConvertUtils.copyProperties(dto, response);
		return response;
	}

	public static UserDto convertFromUsersUpdateRequest(Long id, UserUpdateRequest request) {
		final UserDto userDto = new UserDto();
		ConvertUtils.copyProperties(request, userDto);
		userDto.setUserId(id);
		return userDto;
	}
}
