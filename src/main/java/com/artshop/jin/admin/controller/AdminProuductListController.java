package com.artshop.jin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.artshop.jin.admin.object.AdminProductListObject;
import com.artshop.jin.admin.service.AdminProductListService;

@Controller
public class AdminProuductListController {
	@Autowired
	private AdminProductListService adminProductListService;

	//商品情報が画面に表示される
	@GetMapping("/productlist")
	public ModelAndView poductList() {
		ModelAndView mav = new ModelAndView("product-list");

		List<AdminProductListObject> adminProductList = adminProductListService.getProductInfoList();
		mav.addObject("adminProductList", adminProductList);

		return mav;
	}

}
