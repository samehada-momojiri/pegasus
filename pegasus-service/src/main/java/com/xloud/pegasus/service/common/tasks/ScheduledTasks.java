package com.xloud.pegasus.service.common.tasks;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xloud.pegasus.common.domain.repository.model.User;
import com.xloud.pegasus.common.utils.DateUtils;
import com.xloud.pegasus.service.domainbl.UserCacheBL;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

	private final UserCacheBL userCacheBL;

	@Scheduled(initialDelay = 60000, fixedRate = 60 * 60 * 1000)
	public void doSomething() {
		User user = userCacheBL.findById(5L);
		LOGGER.info("### User : {}", user);

		LOGGER.info("### This time is : {}", DateUtils.format(new Date()));
	}
}
