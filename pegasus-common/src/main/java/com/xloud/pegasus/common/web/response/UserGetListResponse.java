package com.xloud.pegasus.common.web.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGetListResponse extends ApiResponseBaseDto {

	private static final long serialVersionUID = 1L;

	private List<User> users = Lists.newArrayList();

	@Data
	public static class User {
		private String userId;
		private String userName;
		private String mailAddress;
	}

}