package com.xloud.pegasus.common.web.request;

import lombok.Data;

@Data
public class UserCreateRequest {
	private String userName;
	private String mailAddress;
	private String password;
	private String confirmPassword;
}
