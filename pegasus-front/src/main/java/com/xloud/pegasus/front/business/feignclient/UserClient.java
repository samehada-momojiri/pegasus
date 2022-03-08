package com.xloud.pegasus.front.business.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xloud.pegasus.common.web.response.UserGetResponse;

@FeignClient(name = "UserClient", url = "http://localhost:8020/pegasus-service/users")
public interface UserClient {

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	UserGetResponse get(@PathVariable("id") Long id);

}
