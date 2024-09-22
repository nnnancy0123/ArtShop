package com.artshop.jin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.artshop.jin.admin.object.AdminUserListObject;
import com.artshop.jin.admin.service.AdminUserListService;

@Controller
public class AdminUserListController {
	@Autowired
	private AdminUserListService adminUserListService;

	//ユーザー管理リストが画面表示される
	@GetMapping("/adminuserlist")
	public ModelAndView adminUserList() {

		ModelAndView mav = new ModelAndView("user-list");

		List<AdminUserListObject> adminUserInfoList = adminUserListService.getUserInfoList();
		mav.addObject("adminUserInfoList", adminUserInfoList);

		return mav;
	}

}
