package com.xloud.pegasus.common.exception;

import java.util.List;

import com.google.common.collect.Lists;
import com.xloud.pegasus.common.ErrorMessageDto;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {

	private static final long serialVersionUID = 8693467754042779368L;

	private List<ErrorMessageDto> errors = Lists.newArrayList();

	public BusinessException() {
		super();
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(List<ErrorMessageDto> errors) {
		super();
		this.errors = errors;
	}

	public BusinessException(String errorCode, String errorMessage) {
		this.errors = Lists.newArrayList(new ErrorMessageDto(errorCode, errorMessage));
	}

	public BusinessException(String errorCode, String errorMessage, Throwable cause) {
		super(cause);
		this.errors = Lists.newArrayList(new ErrorMessageDto(errorCode, errorMessage));
	}

}
