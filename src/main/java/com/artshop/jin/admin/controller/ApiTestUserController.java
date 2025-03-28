package com.artshop.jin.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artshop.jin.admin.entity.ApiTestUserEntity;
import com.artshop.jin.admin.repository.ApiTestUserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiTestUserController {

	@Autowired
	private ApiTestUserRepository apiTestUserRepository;

	@PostMapping
	public ResponseEntity<ApiTestUserEntity> createUser(@RequestBody ApiTestUserEntity userInfo) {
		//この処理はデータベースまだ設定していない際の処理
		//		userInfo.setUserId(currentId++);
		//		users.add(userInfo);

		ApiTestUserEntity savedUserInfo = apiTestUserRepository.save(userInfo);
		System.out.println("新規ユーザ: " + userInfo.getUserId() + ", " + userInfo.getUserName() + ", " + userInfo.getEmail()
				+ ", " + userInfo.getPassword());
		return ResponseEntity.ok(savedUserInfo);

	}

	@GetMapping
	public ResponseEntity<List<ApiTestUserEntity>> getUsersInfo() {
		return ResponseEntity.ok(apiTestUserRepository.findAll( ));

	}

}
