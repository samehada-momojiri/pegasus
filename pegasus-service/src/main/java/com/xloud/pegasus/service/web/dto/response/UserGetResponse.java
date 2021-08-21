package com.xloud.pegasus.service.web.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGetResponse {

	private String userId;
	private String userName;
	private String mailAddress;

}
