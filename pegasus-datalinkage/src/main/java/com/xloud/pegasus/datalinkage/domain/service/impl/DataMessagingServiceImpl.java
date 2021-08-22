package com.xloud.pegasus.datalinkage.domain.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.xloud.pegasus.common.domain.service.dto.ValueObjectDto;
import com.xloud.pegasus.common.exception.BusinessException;
import com.xloud.pegasus.common.utils.JsonUtils;
import com.xloud.pegasus.datalinkage.domain.service.DataMessagingService;
import com.xloud.pegasus.datalinkage.domain.service.dto.PublishEnvelopeDto;
import com.xloud.pegasus.datalinkage.domain.service.dto.SubscriptionEnvelopeDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataMessagingServiceImpl implements DataMessagingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataMessagingServiceImpl.class);

	private final StringRedisTemplate redisTemplate;

	@Override
	public void publish(PublishEnvelopeDto publishEnvelopeDto) throws BusinessException {

		LOGGER.info("# KEY: {}", publishEnvelopeDto.getMessageKey());
		String json = JsonUtils.convertToJson(publishEnvelopeDto.getValueObjectDto());
		LOGGER.info("# JSON: {}", json);
		Long result = redisTemplate.opsForList().leftPush(publishEnvelopeDto.getMessageKey(), json);
		LOGGER.info("# RESULT: {}", result);

		if (result == null || result == 0) {
			throw new BusinessException("EDSFD0001", "メッセージの登録に失敗しました");
		}
	}

	@Override
	public SubscriptionEnvelopeDto subscription(String messageKey) throws BusinessException {
		SubscriptionEnvelopeDto subscriptionEnvelopeDto = new SubscriptionEnvelopeDto();
		List<Object> resultList = redisTemplate.executePipelined(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
				for (int i = 0; i < 100; i++) {
					stringRedisConn.rPop(messageKey);
				}
				return null;
			}
		});
		LOGGER.info("# RESULT: {}", resultList);

		List<ValueObjectDto> valueObjectDtoList = Lists.newArrayList();
		subscriptionEnvelopeDto.setValueObjectDtoList(valueObjectDtoList);

		for (Object obj : resultList) {
			if (obj == null) {
				break;
			}
			valueObjectDtoList.add(JsonUtils.convertFromJson(obj.toString(), ValueObjectDto.class));
		}

		subscriptionEnvelopeDto.setValueObjectDtoList(valueObjectDtoList);

		return subscriptionEnvelopeDto;
	}

}
