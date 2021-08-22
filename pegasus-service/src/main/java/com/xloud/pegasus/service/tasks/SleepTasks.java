package com.xloud.pegasus.service.tasks;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xloud.pegasus.common.bl.DataLinkageBL;
import com.xloud.pegasus.common.domain.repository.model.User;
import com.xloud.pegasus.common.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SleepTasks {
	private static final Logger LOGGER = LoggerFactory.getLogger(SleepTasks.class);

	@Value("${tasks.sleep-tasks.max-sleep-millisecond}")
	private long MAX_SLEEP_MILLISECOND;

	@Value("${tasks.sleep-tasks.sleep-millisecond}")
	private long SLEEP_MILLISECOND;

	private final StringRedisTemplate redisTemplate;

	private final DataLinkageBL dataLinkageBL;

	private int skipCount = 0;

	@Scheduled(initialDelayString = "${tasks.sleep-tasks.initial-delay}", fixedRateString = "${tasks.sleep-tasks.fixed-rate}")
	public void doSomething() {
		try {

			String suspendFlg = redisTemplate.opsForValue().get("Cache::SleepTasks::suspend");
			if (StringUtils.isNotEmpty(suspendFlg)) {
				LOGGER.info("### SleepTasks is suspending");
				return;
			}

			sleep();

			// データ取得
			// String data = redisTemplate.opsForValue().get("Cache::SleepTasks::data");
			List<User> userList = dataLinkageBL.subscription("AA", "UserInfo", null, User.class);

			if (userList.isEmpty()) {
				LOGGER.info("### Data not exists");
				skipCount++;
				return;
			}
			LOGGER.info("### Data exists");
			skipCount = 0;

			// 主処理（ダミー）
			try {
				for (User user : userList) {
					LOGGER.info("### User: {}", user);
				}
				Thread.sleep(1500L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private void sleep() {
		if (skipCount == 0) {
			return;
		}
		long sleepMillisecond = skipCount * SLEEP_MILLISECOND;
		sleepMillisecond = (sleepMillisecond > MAX_SLEEP_MILLISECOND) ? MAX_SLEEP_MILLISECOND : sleepMillisecond;
		LOGGER.info("### Sleep: {}", sleepMillisecond);
		try {
			Thread.sleep(sleepMillisecond);
		} catch (InterruptedException e) {
			// NOP
		}
	}
}
