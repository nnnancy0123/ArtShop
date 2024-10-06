package com.artshop.jin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.artshop.jin.admin.object.ShopUsersObject;
import com.artshop.jin.admin.service.ShopUsersService;

/**
 * ユーザー管理クラス
 * @author Nancy
 * @sinces  2024-09-30
 */
@RestController
public class ShopUsersController {
	@Autowired
	private ShopUsersService shopUsersService;

	/**
	 * ユーザー管理リストが画面表示される
	 * @return mav
	 */
	@GetMapping("/adminuserlist")
	public ModelAndView shopUserList() {

		ModelAndView mav = new ModelAndView("user-list");

		List<ShopUsersObject> adminUserInfo = shopUsersService.getUserInfoList();
		mav.addObject("adminUserInfo", adminUserInfo);

		return mav;
	}

//	/**
//	 * ユーザーIDでユーザー情報を取得する
//	 * @param userId
//	 * @return
//	 */
//	@GetMapping("/getUserInfo")
//	public ResponseEntity<ShopUsersObject> getUserInfo(@RequestParam("userId") Long userId) {
//		ShopUsersObject userInfoById = shopUsersService.getUserInfoById(userId);
//		//ユーザーが存在することを確認
//		if (userInfoById != null) {
//			return ResponseEntity.ok(userInfoById);
//		} else {
//			// ユーザーが存在しない場合は404エラーを返す
//			return ResponseEntity.notFound().build();
//		}
//
//	}
//
//	/**
//	 * ユーザーIDでユーザ情報を更新して保存する
//	 * @param adminUserListObject
//	 * @return
//	 */
//	@PostMapping("/saveUserInfo")
//	public ResponseEntity<ShopUsersObject> saveUserInfoById(@RequestBody ShopUsersObject shopUsersObject) {
//
//		ShopUsersObject updateUserInfo = shopUsersService.saveUserinfoById(shopUsersObject.getUsersId(),
//				shopUsersObject.getUsersName(),
//				shopUsersObject.getUsersMail(),
//				shopUsersObject.getUsersRoles(),
//				shopUsersObject.getUsersPoints(),
//				shopUsersObject.getPostCode(),
//				shopUsersObject.getUsersAddress1(),
//				shopUsersObject.getUsersAddress2(),
//				shopUsersObject.getUsersAddress3(),
//				shopUsersObject.getDelFlag());
//
//		//ユーザー存在をチェックする
//		if (updateUserInfo != null) {
//			//成功する時、HTTPステータスコード200を設定する
//			return ResponseEntity.ok(updateUserInfo);
//		}
//		// ユーザが見つからない時、エラーメッセージを返す
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//	}
//
//	/**
//	 * 
//	 * @param userId
//	 * @return
//	 */
//	@PostMapping("/delUserInfo")
//	public ResponseEntity<String> delUserInfo(@RequestParam("userId") Long userId) {
//		try {
//			shopUsersService.delUserInfo(userId);
//			//成功する時、削除メッセージを表示される
//			return ResponseEntity.ok("ユーザーが削除されました。");
//		} catch (EntityNotFoundException e) {
//			//エラー処理
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//	}
}
