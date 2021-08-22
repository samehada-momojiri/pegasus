package com.xloud.pegasus.datalinkage.domain.service;

import com.xloud.pegasus.common.exception.BusinessException;
import com.xloud.pegasus.datalinkage.domain.service.dto.PublishEnvelopeDto;
import com.xloud.pegasus.datalinkage.domain.service.dto.SubscriptionEnvelopeDto;

public interface DataMessagingService {

	void publish(PublishEnvelopeDto publishEnvelopeDto) throws BusinessException;

	SubscriptionEnvelopeDto subscription(String messageKey) throws BusinessException;

}
