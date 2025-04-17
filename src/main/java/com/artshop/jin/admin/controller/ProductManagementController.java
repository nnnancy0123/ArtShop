package com.artshop.jin.admin.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.artshop.jin.admin.service.ProductManagementService;

/**
 * 商品情報管理コントローラ
 * @author Nancy
 * @since 2025-03-28
 * @version 2.1
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductManagementController {

	@Autowired
	private ProductManagementService adminProductManagementService;

	/**
	 * 商品登録
	 * @param productName 商品名
	 * @param productDescription 商品紹介
	 * @param categoryName 商品カテゴリ
	 * @param price 商品価格
	 * @param stockQuantity 在庫数
	 * @param stockStatus 販売状態（0=販売中, 1=停止中）
	 * @param delFlag 表示フラグ（0=表示, 1=非表示）
	 * @param productPhoto 商品画像
	 * @return 登録結果（商品情報DTO）
	 */
	@PostMapping
	public ResponseEntity<?> createProductInfo(
			@RequestParam("productName") String productName,
			@RequestParam("productDescription") String productDescription,
			@RequestParam("categoryName") String categoryName,
			@RequestParam("price") BigDecimal price,
			@RequestParam("stockQuantity") int stockQuantity,
			@RequestParam("stockStatus") String stockStatus,
			@RequestParam("delFlag") String delFlag,
			@RequestParam(value = "productPhoto", required = false) MultipartFile productPhoto) {

		ProductInfoDto adminProductManagementDto = new ProductInfoDto();
		adminProductManagementDto.setProductName(productName);
		adminProductManagementDto.setProductDescription(productDescription);
		adminProductManagementDto.setCategoryName(categoryName);
		adminProductManagementDto.setPrice(price);
		adminProductManagementDto.setStockQuantity(stockQuantity);
		adminProductManagementDto.setStockStatus(stockStatus);
		adminProductManagementDto.setDelFlag(delFlag);

		if (productPhoto != null && !productPhoto.isEmpty()) {
			adminProductManagementDto.setProductPhoto(productPhoto.getOriginalFilename());
		}

		try {
			ProductInfoDto result = adminProductManagementService.createProductInfo(adminProductManagementDto,
					productPhoto);
			return ResponseEntity.ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("商品登録に失敗しました");
		}
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
