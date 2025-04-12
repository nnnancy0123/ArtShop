package com.artshop.jin.admin.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artshop.jin.admin.dto.ProductInfoDto;
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
	private AdminProductManagementService adminProductManagementService;

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
	public ResponseEntity<ProductInfoDto> createProductInfo(
			@RequestParam("productName") String productName,
			@RequestParam("productDescription") String productDescription,
			@RequestParam("categoryName") String categoryName,
			@RequestParam("price") BigDecimal price,
			@RequestParam("stockQuantity") int stockQuantity,
			@RequestParam("stockStatus") String stockStatus,
			@RequestParam(value = "productPhoto", required = false) MultipartFile productPhoto) {

		ProductInfoDto adminProductManagementDto = new ProductInfoDto();
		adminProductManagementDto.setProductName(productName);
		adminProductManagementDto.setProductDescription(productDescription);
		adminProductManagementDto.setCategoryName(categoryName);
		adminProductManagementDto.setPrice(price);
		adminProductManagementDto.setStockQuantity(stockQuantity);
		adminProductManagementDto.setStockStatus(stockStatus);

		if (productPhoto != null && !productPhoto.isEmpty()) {
			adminProductManagementDto.setProductPhoto(productPhoto.getOriginalFilename());
		}

		ProductInfoDto result = adminProductManagementService.createProductInfo(adminProductManagementDto);
		return ResponseEntity.ok(result);
	}

	/**
	 * 全ての商品情報を検索する
	 * @return 全ての商品情報
	 */
	@GetMapping
	public ResponseEntity<List<ProductInfoDto>> getProductInfo() {

		List<ProductInfoDto> alllProductInfo = adminProductManagementService.findAllProductInfo();
		return ResponseEntity.ok(alllProductInfo);
	}

	/**
	 * 商品IDによって商品情報を削除する
	 * @param id
	 * @return メッセージ
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleletProductInfo(@PathVariable Long id) {

		adminProductManagementService.deleteProductById(id);
		return ResponseEntity.ok("削除成功しました");
	}

	/**
	 * 編集した商品情報を更新
	 * @param productInfo
	 * @return newProductInfo
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProductInfoDto> updateProductInfo(@PathVariable Long id,
			@ModelAttribute ProductInfoDto productInfo) {

		//パスのIDとリクエストボディのIDを一致させるために設定
		productInfo.setProductId(id);

		ProductInfoDto updateProductInfo = adminProductManagementService.updateProductInfo(productInfo);
		return ResponseEntity.ok(updateProductInfo);
	}
}
