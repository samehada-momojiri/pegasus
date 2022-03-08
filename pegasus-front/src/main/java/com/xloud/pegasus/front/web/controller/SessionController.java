package com.xloud.pegasus.front.web.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xloud.pegasus.constants.CommonConstants;
import com.xloud.pegasus.front.web.session.SessionInfo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(CommonConstants.URL_BASE_MPA + "/session")
@RequiredArgsConstructor
public class SessionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);

	private final SessionInfo sessionInfo;

	@GetMapping(value = "/")
	public String getTime(Model model, HttpServletRequest request) {
		if (sessionInfo.getStartedAt() == null) {
			sessionInfo.setClientIp(request.getRemoteAddr());
			sessionInfo.setStartedAt(LocalDateTime.now());
			sessionInfo.setLastAccessedAt(LocalDateTime.now());
			sessionInfo.setMessage("Session start.");
		} else {
			sessionInfo.setLastAccessedAt(LocalDateTime.now());
			sessionInfo.setMessage("Session is continuing.");
		}
		return "session/index";
	}

	@PostMapping(value = "/send")
	public String doSend(SendForm form, HttpServletRequest request) {
		sessionInfo.setLastAccessedAt(LocalDateTime.now());
		sessionInfo.setMessage(form.getParam());
		request.getSession().setAttribute("param", form.getParam());
		return "session/index";
	}

	@GetMapping(value = "/nop")
	public String doNop(SendForm form, HttpServletRequest request) {
		LOGGER.info("#LastAccessedAt: {}", sessionInfo.getLastAccessedAt());
		LOGGER.info("#Message: {}", sessionInfo.getMessage());
		LOGGER.info("#Param: {}", request.getSession().getAttribute("param"));
		try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "session/index";
	}

	@Data
	public static class SendForm {
		private String param;
	}
}
