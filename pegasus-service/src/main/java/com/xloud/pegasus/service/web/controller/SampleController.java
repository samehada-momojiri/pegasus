package com.xloud.pegasus.service.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xloud.pegasus.common.exception.SystemException;
import com.xloud.pegasus.common.web.response.SampleResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping(value = "/sample", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class SampleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

	@Value("${application.business.users.search-limit-count}")
	private Integer pageLimit;

	@CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
	@RateLimiter(name = "backendA")
	@RequestMapping(method = RequestMethod.GET, path = "/query1")
	public SampleResponse query1(@RequestParam(value = "param", required = false) List<String> param) {
		LOGGER.info("#param: {}", param);
		if (param == null) {
			throw new SystemException("System Error!");
		}
		SampleResponse response = new SampleResponse();
		response.setMessage("success1!");
		return response;
	}

	@CircuitBreaker(name = "backendB")
	@RateLimiter(name = "backendB")
	@RequestMapping(method = RequestMethod.GET, path = "/query2")
	public SampleResponse query1(@RequestParam("param") String param) {
		LOGGER.info("#param: {}", param);
		if (StringUtils.isNotEmpty(param) && "error".equals(param)) {
			throw new SystemException("System Error!");
		}
		SampleResponse response = new SampleResponse();
		response.setMessage("success2!");
		return response;
	}

	public SampleResponse fallback(List<String> param, Throwable t) {
		LOGGER.info("#param: {}", param);
		LOGGER.info("#Throwable: {}", t);
		SampleResponse response = new SampleResponse();
		response.setMessage("fallback!");
		return response;
	}
}
