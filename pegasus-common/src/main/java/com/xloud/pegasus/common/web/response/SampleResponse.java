package com.xloud.pegasus.common.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SampleResponse {
	private String message;
}
