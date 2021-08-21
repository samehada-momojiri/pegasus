package com.xloud.pegasus.service.web.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xloud.pegasus.service.web.dto.response.UserGetResponse;

@FeignClient(name = "usersTest", url = "http://localhost:8020/")
public interface UserControllerTestFeignClient {
	@RequestMapping(method = RequestMethod.GET, value = "/pegasus-service/users/{id}")
	ResponseEntity<UserGetResponse> getUser(@PathVariable("id") Long id);
}