package com.xloud.pegasus.common.exception;

import lombok.Getter;

@Getter
public class ApiCallException extends BusinessException {

	private static final long serialVersionUID = 1L;

	private Integer httpStatus;

	public ApiCallException(Integer httpStatus, Throwable t) {
		super(t);
		this.httpStatus = httpStatus;
	}

	public ApiCallException(Throwable t) {
		super(t);
	}

}
