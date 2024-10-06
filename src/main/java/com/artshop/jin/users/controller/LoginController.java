package com.artshop.jin.users.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * ユーザーログイン画面クラス
 * @author Nancy
 * @since 2024-09-30
 */
@Controller
public class LoginController {
	
	//画面にログインが表示される
	@GetMapping("/login")
	public ModelAndView login() {

		ModelAndView mav = new ModelAndView("account-signin");

		return mav;

	}

}
