package com.snapscreenapp.supervisor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdministrationController {
	@RequestMapping("/supervisor")
	public String supervisor(@RequestParam(value = "name", required = false, defaultValue = "Default Name") String name,
			Model model) {
		model.addAttribute("name", name);
		return "supervisor";
	}

	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
}