package com.regi.usermanagement;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class UserManagementController {
	
	@RequestMapping("/")
	@ResponseBody
	public String sayHello() {
		return "Szia Janos!!!";
	}
}

