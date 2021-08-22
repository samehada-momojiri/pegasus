package com.xloud.pegasus.datalinkage.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xloud.pegasus.common.domain.service.dto.ValueObjectDto;
import com.xloud.pegasus.common.exception.BusinessException;
import com.xloud.pegasus.common.web.request.DataLinkagePublishRequestDto;
import com.xloud.pegasus.common.web.request.DataLinkageSubscriptionRequestDto;
import com.xloud.pegasus.common.web.response.ApiResponseBaseDto;
import com.xloud.pegasus.common.web.response.DataLinkageSubscriptionResponseDto;
import com.xloud.pegasus.constants.CommonConstants;
import com.xloud.pegasus.datalinkage.domain.service.DataMessagingService;
import com.xloud.pegasus.datalinkage.domain.service.dto.PublishEnvelopeDto;
import com.xloud.pegasus.datalinkage.domain.service.dto.SubscriptionEnvelopeDto;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping(value = "/messages", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class DataMessagingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataMessagingController.class);

	private final DataMessagingService dataMessagingService;

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public ApiResponseBaseDto doPublish(@Validated @RequestBody DataLinkagePublishRequestDto request) {
		LOGGER.info("#DataMessagingController.doPublish() called.");
		LOGGER.info("#DataLinkagePublishRequestDto : {}", request);

		ApiResponseBaseDto response = new ApiResponseBaseDto();

		PublishEnvelopeDto publishEnvelopeDto = new PublishEnvelopeDto();
		publishEnvelopeDto.setMessageKey(
				createMessageKey(request.getNameSpace(), request.getInterfaceName(), request.getIdentifier()));
		ValueObjectDto valueObjectDto = new ValueObjectDto();
		valueObjectDto.setType(request.getType());
		valueObjectDto.setData(request.getData());
		publishEnvelopeDto.setValueObjectDto(valueObjectDto);

		try {
			dataMessagingService.publish(publishEnvelopeDto);
		} catch (BusinessException e) {
			response.setApiResponseResult(CommonConstants.API_RESPONSE_ERROR);
			response.setErrors(e.getErrors());
			return response;
		}
		response.setApiResponseResult(CommonConstants.API_RESPONSE_SUCCESS);
		return response;
	}

	@RequestMapping(value = "/subscription", method = RequestMethod.POST)
	public DataLinkageSubscriptionResponseDto doSubscription(
			@Validated @RequestBody DataLinkageSubscriptionRequestDto request) {
		LOGGER.info("#DataMessagingController.doSubscription() called.");
		LOGGER.info("#DataLinkageSubscriptionRequestDto : {}", request);

		DataLinkageSubscriptionResponseDto response = new DataLinkageSubscriptionResponseDto();

		try {
			String messageKey = createMessageKey(request.getNameSpace(), request.getInterfaceName(),
					request.getIdentifier());
			SubscriptionEnvelopeDto subscriptionEnvelopeDto = dataMessagingService.subscription(messageKey);

			response.setNameSpace(request.getNameSpace());
			response.setInterfaceName(request.getInterfaceName());
			response.setIdentifier(request.getIdentifier());
			response.setValueObjectDtoList(subscriptionEnvelopeDto.getValueObjectDtoList());

		} catch (BusinessException e) {
			response.setApiResponseResult(CommonConstants.API_RESPONSE_ERROR);
			response.setErrors(e.getErrors());
			return response;
		}
		response.setApiResponseResult(CommonConstants.API_RESPONSE_SUCCESS);
		return response;
	}

	private String createMessageKey(String nameSpace, String interfaceName, String identifier) {
		StringBuilder result = new StringBuilder();
		result.append("Cache");
		result.append("::");
		result.append(nameSpace);
		result.append("::");
		result.append(interfaceName);
		if (StringUtils.isNotEmpty(identifier)) {
			result.append("::");
			result.append(identifier);
		}
		return result.toString();
	}

}