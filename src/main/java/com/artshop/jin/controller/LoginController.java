package com.artshop.jin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	//画面にログインが表示される
	@GetMapping("/login")
	public ModelAndView login() {

		ModelAndView mav = new ModelAndView("account-signin");

		return mav;

	}

}
