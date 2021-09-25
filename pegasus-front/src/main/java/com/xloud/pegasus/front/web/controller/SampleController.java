package com.xloud.pegasus.front.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomUtils;
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

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
@RequestMapping(CommonConstants.URL_BASE_MPA + "/sample")
public class SampleController {

	@GetMapping(value = "/time")
	public String getTime(Model model) {
		Date threadDateTime = DateUtils.getThreadDateTime();
		String dateTime = DateUtils.format(threadDateTime);

		model.addAttribute("dateTime", dateTime);

		return "sample/time";
	}

	@GetMapping(value = "/basic")
	public String getBasic(Model model) {
		int nextInt = RandomUtils.nextInt(1, 4);
		model.addAttribute("priority", nextInt);

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
		System.out.println("*******************************************************************");
		System.out.println(JsonUtils.convertToJson(userForm));
		System.out.println("*******************************************************************");
		initModel(model);
		return "sample/input";
	}

	private void initModel(Model model) {
		Map<String, String> sexMap = Maps.newLinkedHashMap();
		sexMap.put("", "");
		sexMap.put("1", "男性");
		sexMap.put("2", "女性");
		sexMap.put("3", "その他");
		model.addAttribute("sexMap", sexMap);

		Map<String, String> authorityMap = Maps.newLinkedHashMap();
		authorityMap.put("1", "一般");
		authorityMap.put("2", "リーダー");
		authorityMap.put("3", "管理者");
		model.addAttribute("authorityMap", authorityMap);

		Map<String, String> availableFunctionMap = Maps.newLinkedHashMap();
		availableFunctionMap.put("Z1", "ユーザ管理");
		availableFunctionMap.put("Z2", "権限管理");
		availableFunctionMap.put("A1", "顧客管理");
		availableFunctionMap.put("B1", "商品管理");
		availableFunctionMap.put("C1", "受注管理");
		availableFunctionMap.put("D1", "発注管理");
		availableFunctionMap.put("E1", "請求管理");
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
		private String sex;
		@NotEmpty
		private String authority;
		private String readOnly;
		@NotEmpty
		private List<String> availableFunction = Lists.newArrayList();
	}

}
