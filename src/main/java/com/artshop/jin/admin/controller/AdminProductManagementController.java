package com.artshop.jin.admin.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artshop.jin.admin.dto.AdminProductManagementDto;
import com.artshop.jin.admin.service.AdminProductManagementService;

/**
 * 商品情報管理コントローラ
 * @author Nancy
 * @since 2025-03-28
 * @version 2.1
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminProductManagementController {

	@Autowired
	private AdminProductManagementService productService;

	/**
	 * 商品登録
	 * @param productName 商品名
	 * @param productDescription 商品紹介
	 * @param categoryName 商品カテゴリ
	 * @param price 商品価格
	 * @param stockQuantity 在庫数
	 * @param stockStatus 販売状態（true=上架）
	 * @param productPhoto 商品画像
	 * @return 登録結果（商品情報DTO）
	 */
	@PostMapping
	public ResponseEntity<AdminProductManagementDto> saveProductInfo(
			@RequestParam("productName") String productName,
			@RequestParam("productDescription") String productDescription,
			@RequestParam("categoryName") String categoryName,
			@RequestParam("price") BigDecimal price,
			@RequestParam("stockQuantity") int stockQuantity,
			@RequestParam("stockStatus") boolean stockStatus,
			@RequestParam(value = "productPhoto", required = false) MultipartFile productPhoto) {

		AdminProductManagementDto adminProductManagementDto = new AdminProductManagementDto();
		adminProductManagementDto.setProductName(productName);
		adminProductManagementDto.setProductDescription(productDescription);
		adminProductManagementDto.setCategoryName(categoryName);
		adminProductManagementDto.setPrice(price);
		adminProductManagementDto.setStockQuantity(stockQuantity);
		adminProductManagementDto.setStockStatus(stockStatus);

		if (productPhoto != null && !productPhoto.isEmpty()) {
			adminProductManagementDto.setProductPhoto(productPhoto.getOriginalFilename());
		}

		AdminProductManagementDto result = productService.saveProductInfo(adminProductManagementDto);
		return ResponseEntity.ok(result);
	}

	@GetMapping
	public ResponseEntity<List<AdminProductManagementDto>> getProductInfo() {

		List<AdminProductManagementDto> alllProductInfo = productService.findAllProductInfo();
		return ResponseEntity.ok(alllProductInfo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleletProductInfo(@PathVariable Long id) {
		productService.deleteProductById(id);
		return ResponseEntity.ok("削除成功しました");
	}
}
