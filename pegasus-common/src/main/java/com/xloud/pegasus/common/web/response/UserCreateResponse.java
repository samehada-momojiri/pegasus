package com.xloud.pegasus.common.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreateResponse {

	private String userId;
	private String userName;
	private String mailAddress;
	private String registTime;
	private String updateTime;

}
