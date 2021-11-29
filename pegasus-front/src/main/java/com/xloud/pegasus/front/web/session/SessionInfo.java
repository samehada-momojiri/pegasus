package com.xloud.pegasus.front.web.session;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SessionInfo implements Serializable {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = -6228456085202936272L;

	private String clientIp;
	private String startedAt;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String host) {
		this.clientIp = host;
	}

	public String getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(String startedAt) {
		this.startedAt = startedAt;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(startedAt);
	}

}