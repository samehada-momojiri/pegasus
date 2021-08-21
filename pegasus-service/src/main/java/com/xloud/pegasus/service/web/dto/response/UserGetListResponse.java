package com.xloud.pegasus.service.web.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGetListResponse {

	private List<User> users = Lists.newArrayList();

	@Data
	public static class User {
		private String userId;
		private String userName;
		private String mailAddress;
	}

}