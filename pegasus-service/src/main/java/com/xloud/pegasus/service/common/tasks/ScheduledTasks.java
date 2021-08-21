package com.xloud.pegasus.service.common.tasks;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xloud.pegasus.service.common.utils.DateUtils;

@Component
public class ScheduledTasks {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

	@Scheduled(initialDelay = 60000, fixedRate = 10000)
	public void doSomething() {
		LOGGER.info("### This time is : ", DateUtils.format(new Date()));
	}
}
