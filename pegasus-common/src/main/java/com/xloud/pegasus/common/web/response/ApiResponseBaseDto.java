package com.xloud.pegasus.common.web.response;

import java.util.List;

import com.xloud.pegasus.common.ErrorMessageDto;

import lombok.Data;

@Data
public class ApiResponseBaseDto {
	private String apiResponseResult;
	private List<ErrorMessageDto> errors;
}
