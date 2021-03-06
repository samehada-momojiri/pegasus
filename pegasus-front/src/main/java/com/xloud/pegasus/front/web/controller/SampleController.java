package com.xloud.pegasus.front.web.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xloud.pegasus.common.utils.DateUtils;
import com.xloud.pegasus.common.utils.JsonUtils;
import com.xloud.pegasus.constants.CommonConstants;
import com.xloud.pegasus.front.web.session.SessionInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(CommonConstants.URL_BASE_MPA + "/sample")
@RequiredArgsConstructor
public class SampleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

	private final SessionInfo sessionInfo;

	@GetMapping(value = "/time")
	public String getTime(Model model) {
		if (sessionInfo.getStartedAt() == null) {
			// sessionInfo.setClientIp(request.getRemoteAddr());
			sessionInfo.setStartedAt(LocalDateTime.now());
			sessionInfo.setMessage("Session start.");
		} else {
			sessionInfo.setMessage("Session is continuing.");
		}

		Date threadDateTime = DateUtils.getThreadDateTime();
		String dateTime = DateUtils.format(threadDateTime);

		model.addAttribute("dateTime", dateTime);

		return "sample/time";
	}

	@GetMapping(value = "/basic")
	public String getBasic(Model model) {
		int nextInt = RandomUtils.nextInt(1, 4);
		model.addAttribute("priority", nextInt);

		model.addAttribute("prefix", "Prefix");
		model.addAttribute("body", "Body");
		model.addAttribute("str", "Hello,World!");
		model.addAttribute("longstr", "XXXXXXXX10XXXXXXXX20XXXXXXXX30");

		List<Player> playerList = Lists.newArrayList();
		playerList.add(new Player(100, "M.Jordan", "Chicago", DateUtils.parse("1963-02-17")));
		playerList.add(new Player(200, "L.James", "Lakers", DateUtils.parse("1984-12-30")));
		playerList.add(new Player(300, "S.Curry", "Warriors", DateUtils.parse("1988-03-14")));
		playerList.add(new Player(400, "K.Durant", "Nets", DateUtils.parse("1988-09-29")));
		playerList.add(new Player(500, "J.Harden", "Nets", DateUtils.parse("1989-08-26")));
		model.addAttribute("playerList", playerList);

		return "sample/basic";
	}

	@GetMapping(value = "/input")
	public String initInput(Model model) {
		initModel(model);

		UserForm userForm = new UserForm();
		userForm.setAuthority("1");
		model.addAttribute("userForm", userForm);
		return "sample/input";
	}

	@PostMapping(value = "/confirm")
	public String confirmInput(@Validated UserForm userForm, BindingResult bindingResult, Model model) {
		LOGGER.debug("*******************************************************************");
		LOGGER.debug(JsonUtils.convertToJson(userForm));
		LOGGER.debug("*******************************************************************");
		initModel(model);
		return "sample/input";
	}

	@GetMapping(value = "/multi-value/init")
	public String initMultiValueInput(Model model) {
		return "sample/multiInput";
	}

	@PostMapping(value = "/multi-value/confirm")
	public String confirmMultiValueInput(MultiValueForm multiValueForm, BindingResult bindingResult, Model model) {
		LOGGER.debug("*******************************************************************");
		LOGGER.debug(JsonUtils.convertToJson(multiValueForm));
		LOGGER.debug("*******************************************************************");
		return "sample/multiInput";
	}

	private void initModel(Model model) {
		Map<String, String> sexMap = Maps.newLinkedHashMap();
		sexMap.put("", "");
		sexMap.put("1", "??????");
		sexMap.put("2", "??????");
		sexMap.put("3", "?????????");
		model.addAttribute("sexMap", sexMap);

		Map<String, String> authorityMap = Maps.newLinkedHashMap();
		authorityMap.put("1", "??????");
		authorityMap.put("2", "????????????");
		authorityMap.put("3", "?????????");
		model.addAttribute("authorityMap", authorityMap);

		Map<String, String> availableFunctionMap = Maps.newLinkedHashMap();
		availableFunctionMap.put("Z1", "???????????????");
		availableFunctionMap.put("Z2", "????????????");
		availableFunctionMap.put("A1", "????????????");
		availableFunctionMap.put("B1", "????????????");
		availableFunctionMap.put("C1", "????????????");
		availableFunctionMap.put("D1", "????????????");
		availableFunctionMap.put("E1", "????????????");
		model.addAttribute("availableFunctionMap", availableFunctionMap);
	}

	@Data
	@AllArgsConstructor
	public static class Player {
		private Integer no;
		private String name;
		private String team;
		private Date birthday;
	}

	@Data
	public static class UserForm {
		@NotEmpty
		private String name;
		@NotEmpty
		private String mailAddress;
		@NotEmpty
		private String password;
		@NotEmpty
		private String confirmPassword;
		@NotEmpty
		private String birthDay;
		@NotEmpty
		private String hopeDay;
		@NotEmpty
		private String sex;
		@NotEmpty
		private String authority;
		private String readOnly;
		@NotEmpty
		private List<String> availableFunction = Lists.newArrayList();
	}

	@Data
	public static class MultiValueForm {
		private String selectedCode;
		private List<String> checkboxList;
		private Map<String, Map<String, String>> multiValueMap;
		private Map<String, List<String>> code1;
		private Map<String, Map<String, List<String>>> code1Attr;
	}

}
