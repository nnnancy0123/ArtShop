package com.artshop.jin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.artshop.jin.admin.object.AdminUserListObject;
import com.artshop.jin.admin.service.AdminUserListService;

@RestController
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

	// ユーザーIDでユーザー情報を取得する
	@GetMapping("/getUserInfo")
	public ResponseEntity<AdminUserListObject> getUserInfo(@RequestParam("userId") Long userId) {
		AdminUserListObject userInfoById = adminUserListService.getUserInfoById(userId);
		if (userInfoById != null) {
			return ResponseEntity.ok(userInfoById);
		} else {
			// ユーザーが存在しない場合は404エラーを返す
			return ResponseEntity.notFound().build();
		}

	}

}
