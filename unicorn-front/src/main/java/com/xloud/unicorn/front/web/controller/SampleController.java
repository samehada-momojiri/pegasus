package com.xloud.unicorn.front.web.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xloud.pegasus.common.utils.DateUtils;
import com.xloud.pegasus.constants.CommonConstants;

@Controller
@RequestMapping(CommonConstants.URL_BASE_MPA + "/sample")
public class SampleController {

	@GetMapping(value = "/input")
	public String getTime(Model model) {
		Date threadDateTime = DateUtils.getThreadDateTime();
		String dateTime = DateUtils.format(threadDateTime);

		model.addAttribute("dateTime", dateTime);

		return "sample/input";
	}

	@GetMapping(value = "/ajaxFragment")
	public String getMorePage(Model model) {
		UUID randomUUID = UUID.randomUUID();
		model.addAttribute("uuid", randomUUID);
		return "components/ajaxfragment :: cardfragment";
	}
}
