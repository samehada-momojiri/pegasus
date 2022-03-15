package com.xloud.pegasus.service.web.helper;

import java.util.List;
import java.util.stream.Collectors;

import com.xloud.pegasus.common.utils.ConvertUtils;
import com.xloud.pegasus.common.web.request.UserCreateRequest;
import com.xloud.pegasus.common.web.request.UserUpdateRequest;
import com.xloud.pegasus.common.web.response.UserCreateResponse;
import com.xloud.pegasus.common.web.response.UserGetListResponse;
import com.xloud.pegasus.common.web.response.UserGetResponse;
import com.xloud.pegasus.constants.CommonConstants;
import com.xloud.pegasus.common.web.response.UserGetListResponse.User;
import com.xloud.pegasus.service.domain.service.dto.UserDto;

public class UsersHelper {
	public static UserGetListResponse convertToUsersGetListResponse(List<UserDto> list) {
		final UserGetListResponse response = new UserGetListResponse();
		List<User> users = list.stream().map((dto) -> {
			final UserGetListResponse.User user = new UserGetListResponse.User();
			ConvertUtils.copyProperties(dto, user);
			return user;
		}).collect(Collectors.toList());
		response.setApiResponseResult(CommonConstants.API_RESPONSE_SUCCESS);
		response.setHttpStatus(200);
		response.setUsers(users);
		return response;
	}

	public static UserGetResponse convertToUsersGetResponse(UserDto dto) {
		final UserGetResponse response = new UserGetResponse();
		if (dto != null) {
			ConvertUtils.copyProperties(dto, response);
		}
		response.setApiResponseResult(CommonConstants.API_RESPONSE_SUCCESS);
		response.setHttpStatus(200);
		return response;
	}

	public static UserDto convertFromUsersCreateRequest(UserCreateRequest request) {
		final UserDto userDto = new UserDto();
		ConvertUtils.copyProperties(request, userDto);
		return userDto;
	}

	public static UserCreateResponse convertToUsersCreateResponse(UserDto dto) {
		final UserCreateResponse response = new UserCreateResponse();
		if (dto != null) {
			ConvertUtils.copyProperties(dto, response);
		}
		response.setApiResponseResult(CommonConstants.API_RESPONSE_SUCCESS);
		response.setHttpStatus(200);
		return response;
	}

	public static UserDto convertFromUsersUpdateRequest(Long id, UserUpdateRequest request) {
		final UserDto userDto = new UserDto();
		ConvertUtils.copyProperties(request, userDto);
		userDto.setUserId(id);
		return userDto;
	}
}
