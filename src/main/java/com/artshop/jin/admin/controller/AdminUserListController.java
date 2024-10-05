package com.artshop.jin.admin.controller;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;

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

/**
 * ユーザー管理クラス
 * @author Nancy
 * @sinces  2024-09-30
 */
@RestController
public class AdminUserListController {
	@Autowired
	private AdminUserListService adminUserListService;

	/**
	 * ユーザー管理リストが画面表示される
	 * @return mav
	 */
	@GetMapping("/adminuserlist")
	public ModelAndView adminUserList() {

		ModelAndView mav = new ModelAndView("user-list");

		List<AdminUserListObject> adminUserInfoList = adminUserListService.getUserInfoList();
		mav.addObject("adminUserInfoList", adminUserInfoList);

		return mav;
	}

	/**
	 * ユーザーIDでユーザー情報を取得する
	 * @param userId
	 * @return
	 */
	@GetMapping("/getUserInfo")
	public ResponseEntity<AdminUserListObject> getUserInfo(@RequestParam("userId") Long userId) {
		AdminUserListObject userInfoById = adminUserListService.getUserInfoById(userId);
		//ユーザーが存在することを確認
		if (userInfoById != null) {
			return ResponseEntity.ok(userInfoById);
		} else {
			// ユーザーが存在しない場合は404エラーを返す
			return ResponseEntity.notFound().build();
		}

	}

	/**
	 * ユーザーIDでユーザ情報を更新して保存する
	 * @param adminUserListObject
	 * @return
	 */
	@PostMapping("/saveUserInfo")
	public ResponseEntity<AdminUserListObject> saveUserInfoById(@RequestBody AdminUserListObject adminUserListObject) {

		AdminUserListObject updateUserInfo = adminUserListService.saveUserinfoById(adminUserListObject.getUsersId(),
				adminUserListObject.getUsersName(),
				adminUserListObject.getUsersMail(),
				adminUserListObject.getUsersRoles(),
				adminUserListObject.getUsersPoints(),
				adminUserListObject.getPostCode(),
				adminUserListObject.getUsersAddress1(),
				adminUserListObject.getUsersAddress2(),
				adminUserListObject.getUsersAddress3(),
				adminUserListObject.getDelFlag());

		//ユーザー存在をチェックする
		if (updateUserInfo != null) {
			//成功する時、HTTPステータスコード200を設定する
			return ResponseEntity.ok(updateUserInfo);
		}
		// ユーザが見つからない時、エラーメッセージを返す
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@PostMapping("/delUserInfo")
	public ResponseEntity<String> delUserInfo(@RequestParam("userId") Long userId) {
		try {
			adminUserListService.delUserInfo(userId);
			//成功する時、削除メッセージを表示される
			return ResponseEntity.ok("ユーザーが削除されました。");
		} catch (EntityNotFoundException e) {
			//エラー処理
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
