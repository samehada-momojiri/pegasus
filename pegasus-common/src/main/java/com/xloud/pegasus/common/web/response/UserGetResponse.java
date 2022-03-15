package com.xloud.pegasus.common.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserGetResponse extends ApiResponseBaseDto {

	private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String mailAddress;

}
