package com.xloud.pegasus.common.web.response;

import java.io.Serializable;
import java.util.List;

import com.xloud.pegasus.common.ErrorMessageDto;

public class ApiResponseBaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String apiResponseResult;
	private Integer httpStatus;
	private List<ErrorMessageDto> errors;

	public String getApiResponseResult() {
		return apiResponseResult;
	}

	public void setApiResponseResult(String apiResponseResult) {
		this.apiResponseResult = apiResponseResult;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public List<ErrorMessageDto> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorMessageDto> errors) {
		this.errors = errors;
	}

}
