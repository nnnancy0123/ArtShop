package com.artshop.jin.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminProuductListController {

	//商品情報が画面に表示される
	@GetMapping("/productlist")
	public ModelAndView poductList() {
		ModelAndView mav = new ModelAndView("product-list");
		return mav;
	}

}
