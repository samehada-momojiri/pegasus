package com.xloud.pegasus.service.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xloud.pegasus.service.web.dto.request.SampleRequest;
import com.xloud.pegasus.service.web.dto.response.SampleResponse;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping(value = "/sample", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class SampleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

	@Value("${application.business.users.search-limit-count}")
	private Integer pageLimit;

	@RequestMapping(method = RequestMethod.GET, path = "/query1")
	public SampleResponse query1(@RequestParam("param") List<String> param) {
		LOGGER.info("#PageLimit: {}", pageLimit);
		LOGGER.info(String.valueOf(param));
		SampleResponse response = new SampleResponse();
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/query2")
	public SampleResponse query1(SampleRequest request) {
		LOGGER.info(String.valueOf(request));
		SampleResponse response = new SampleResponse();
		return response;
	}

}
