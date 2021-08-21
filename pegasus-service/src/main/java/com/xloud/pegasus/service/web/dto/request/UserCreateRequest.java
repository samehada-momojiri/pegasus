package com.xloud.pegasus.service.web.dto.request;

import lombok.Data;

@Data
public class UserCreateRequest {
	private String userName;
	private String mailAddress;
	private String password;
	private String confirmPassword;
}
