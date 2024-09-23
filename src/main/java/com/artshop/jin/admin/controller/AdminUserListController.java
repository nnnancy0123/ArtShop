package com.artshop.jin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	//ユーザーIDでユーザ情報を更新して保存する
	@PostMapping("/saveUserInfo")
	public ResponseEntity<AdminUserListObject> saveUserInfoById(@RequestBody AdminUserListObject user) {

		AdminUserListObject updateUserInfo = adminUserListService.saveUserinfoById(user.getUsersId(),
				user.getUsersName(),
				user.getUsersMail(),
				user.getUsersRoles(),
				user.getUsersPoints(),
				user.getPostCode(),
				user.getUsersAddress1(),
				user.getUsersAddress2(),
				user.getUsersAddress3());
		;
		if (updateUserInfo != null) {
			//成功する時、HTTPステータスコード200を設定する
			return ResponseEntity.ok(updateUserInfo);
		} else {
			// ユーザが見つからない時、エラーメッセージを返す
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
