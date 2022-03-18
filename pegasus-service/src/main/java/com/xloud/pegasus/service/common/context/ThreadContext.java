package com.xloud.pegasus.service.common.context;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ThreadContext {

	private String sessionId;
	private Date threadDate;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getThreadDate() {
		return threadDate;
	}

	public void setThreadDate(Date threadDate) {
		this.threadDate = threadDate;
	}

}
