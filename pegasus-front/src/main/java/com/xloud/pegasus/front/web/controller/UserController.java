package com.xloud.pegasus.front.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xloud.pegasus.common.web.response.UserGetResponse;
import com.xloud.pegasus.constants.CommonConstants;
import com.xloud.pegasus.front.business.feignclient.UserClient;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(CommonConstants.URL_BASE_MPA + "/users")
@RequiredArgsConstructor
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final UserClient userClient;

	@GetMapping(value = "/")
	public String getTime(Model model, HttpServletRequest request) {
		return "users/index";
	}

	@PostMapping(value = "/search")
	public String doNop(Model model, UserForm form, HttpServletRequest request) {
		UserGetResponse userGetResponse = userClient.get(Long.parseLong(form.getId()));
		LOGGER.info("#UserGetResponse: {}", userGetResponse);
		model.addAttribute("userGetResponse", userGetResponse);
		return "users/index";
	}

	@Data
	public static class UserForm {
		private String id;
	}
}
