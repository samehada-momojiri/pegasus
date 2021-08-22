package com.xloud.pegasus.common.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xloud.pegasus.common.web.request.DataLinkagePublishRequestDto;
import com.xloud.pegasus.common.web.request.DataLinkageSubscriptionRequestDto;
import com.xloud.pegasus.common.web.response.ApiResponseBaseDto;
import com.xloud.pegasus.common.web.response.DataLinkageSubscriptionResponseDto;

@FeignClient(name = "dataMessagingFeignClient", url = "http://localhost:8050/pegasus-datalinkage/messages")
//@FeignClient(name = "dataMessagingFeignClient", url = "${datalinkage.url}")
public interface DataMessagingFeignClient {

//	@RequestMapping(value = "${datalinkage.publish}", method = RequestMethod.POST)
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public ApiResponseBaseDto doPublish(@RequestBody DataLinkagePublishRequestDto request);

//	@RequestMapping(value = "${datalinkage.subscription}", method = RequestMethod.POST)
	@RequestMapping(value = "/subscription", method = RequestMethod.POST)
	public DataLinkageSubscriptionResponseDto doSubscription(@RequestBody DataLinkageSubscriptionRequestDto request);

}
