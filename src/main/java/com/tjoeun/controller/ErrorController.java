package com.tjoeun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorController {
	@GetMapping("/error")
	public String error(@RequestParam String error, Model model) {
		model.addAttribute("error", error);
		return "error";
	}
}
