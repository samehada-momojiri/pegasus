package com.xloud.pegasus.common.bl.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.xloud.pegasus.common.bl.DataLinkageBL;
import com.xloud.pegasus.common.domain.service.dto.ValueObjectDto;
import com.xloud.pegasus.common.exception.BusinessException;
import com.xloud.pegasus.common.exception.SystemException;
import com.xloud.pegasus.common.feignclient.DataMessagingFeignClient;
import com.xloud.pegasus.common.utils.JsonUtils;
import com.xloud.pegasus.common.web.request.DataLinkagePublishRequestDto;
import com.xloud.pegasus.common.web.request.DataLinkageSubscriptionRequestDto;
import com.xloud.pegasus.common.web.response.ApiResponseBaseDto;
import com.xloud.pegasus.common.web.response.DataLinkageSubscriptionResponseDto;
import com.xloud.pegasus.constants.CommonConstants;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLinkageBLImpl implements DataLinkageBL {

	private final DataMessagingFeignClient dataMessagingFeignClient;

	public void publish(String nameSpace, String interfaceName, String identifier, Object obj)
			throws BusinessException {
		DataLinkagePublishRequestDto dataLinkagePublishRequestDto = new DataLinkagePublishRequestDto();
		dataLinkagePublishRequestDto.setNameSpace(nameSpace);
		dataLinkagePublishRequestDto.setInterfaceName(interfaceName);
		dataLinkagePublishRequestDto.setIdentifier(identifier);
		dataLinkagePublishRequestDto.setType(obj.getClass().getName());
		dataLinkagePublishRequestDto.setData(JsonUtils.convertToJson(obj));

		ApiResponseBaseDto responseDto = dataMessagingFeignClient.doPublish(dataLinkagePublishRequestDto);
		if (CommonConstants.API_RESPONSE_ERROR.equals(responseDto.getApiResponseResult())) {
			throw new BusinessException(responseDto.getErrors(), null);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> subscription(String nameSpace, String interfaceName, String identifier, Class<T> claszz)
			throws BusinessException {
		List<T> resultList = Lists.newArrayList();

		DataLinkageSubscriptionRequestDto dataLinkageSubscriptionRequestDto = new DataLinkageSubscriptionRequestDto();
		dataLinkageSubscriptionRequestDto.setNameSpace(nameSpace);
		dataLinkageSubscriptionRequestDto.setInterfaceName(interfaceName);
		dataLinkageSubscriptionRequestDto.setIdentifier(identifier);

		DataLinkageSubscriptionResponseDto responseDto = dataMessagingFeignClient
				.doSubscription(dataLinkageSubscriptionRequestDto);
		if (CommonConstants.API_RESPONSE_ERROR.equals(responseDto.getApiResponseResult())) {
			throw new BusinessException(responseDto.getErrors(), null);
		}

		List<ValueObjectDto> valueObjectDtoList = responseDto.getValueObjectDtoList();

		for (ValueObjectDto valueObjectDto : valueObjectDtoList) {
			try {
				Object obj = JsonUtils.convertFromJson(valueObjectDto.getData(),
						Class.forName(valueObjectDto.getType()));
				resultList.add((T) obj);
			} catch (ClassNotFoundException e) {
				throw new SystemException(e);
			}
		}

		return resultList;
	}
}
