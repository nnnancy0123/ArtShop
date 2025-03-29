package com.artshop.jin.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artshop.jin.admin.dto.AdminProductManagementDto;
import com.artshop.jin.admin.service.AdminProductManagementService;

/**
 * 商品情報管理コントローラ
 * @author Nancy
 * @since 2025-03-28
 * @version 2.0
 */
@RestController
@RequestMapping("/api/adminProductManagement")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminProductManagementController {

	@Autowired
	private AdminProductManagementService adminProductManagementService;

	/**
	 * 商品登録
	 * @param productManagementDto
	 * @return 登録結果
	 */
	@PostMapping
	public ResponseEntity<AdminProductManagementDto> saveProductInfo(
			@RequestBody AdminProductManagementDto productManagementDto) {
		// 商品情報を保存し、保存結果（DTO）を取得する
		AdminProductManagementDto saveProductInfo = adminProductManagementService.saveProductInfo(productManagementDto);
		return ResponseEntity.ok(saveProductInfo);
	}
}
