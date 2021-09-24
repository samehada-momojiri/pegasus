package com.xloud.pegasus.front.web.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xloud.pegasus.common.utils.DateUtils;
import com.xloud.pegasus.constants.CommonConstants;

@Controller
@RequestMapping(CommonConstants.URL_BASE_MPA + "/sample/time")
public class SampleController {

	@GetMapping()
	public String view(Model model) {
		Date threadDateTime = DateUtils.getThreadDateTime();
		String dateTime = DateUtils.format(threadDateTime);

		model.addAttribute("dateTime", dateTime);

		return "sample/time";
	}
}
