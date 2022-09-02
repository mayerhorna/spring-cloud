package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationCodeController {
	@RequestMapping("/authorizationCodeFromSysSentence")
	public String authorizationCodeFromSysSentence(@RequestParam String code, ModelMap modelMap) {
		modelMap.put("authorizationCode", code);
		return "sentence";
	}
}
