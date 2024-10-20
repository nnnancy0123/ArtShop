package com.artshop.jin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.artshop.jin.admin.object.ShopProductsObject;
import com.artshop.jin.admin.service.ShopProductsService;

/**
 * 商品管理クラス
 * @author Nancy
 * @since 2024-09-30
 */
@Controller
public class ShopProuductsController {
	@Autowired
	private ShopProductsService adminProductListService;

	/**
	 * 商品情報が画面に表示される
	 * @return mav
	 */
	@GetMapping("/productlist")
	public ModelAndView poductList() {
		ModelAndView mav = new ModelAndView("product-list");

		List<ShopProductsObject> adminProductList = adminProductListService.getProductInfoList();
		mav.addObject("adminProductList", adminProductList);

		return mav;
	}

}
