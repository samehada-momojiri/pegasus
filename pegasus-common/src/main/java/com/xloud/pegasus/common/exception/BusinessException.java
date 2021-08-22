package com.xloud.pegasus.common.exception;

import java.util.List;

import com.google.common.collect.Lists;
import com.xloud.pegasus.common.ErrorMessageDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends Exception {

	private static final long serialVersionUID = 8693467754042779368L;

	private List<ErrorMessageDto> errors = Lists.newArrayList();
	private Throwable cause;

}
