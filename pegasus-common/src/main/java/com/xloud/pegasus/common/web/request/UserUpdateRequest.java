package com.xloud.pegasus.common.web.request;

import lombok.Data;

@Data
public class UserUpdateRequest {

	private String userName;
	private String mailAddress;
	private String password;
	private String confirmPassword;
	private String deleted;

}