package com.artshop.jin.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artshop.jin.admin.dto.ProductInfoDto;
import com.artshop.jin.admin.entity.ProductInfoEntity;
import com.artshop.jin.admin.repository.AdminProductManagementRepository;

/**
 * 商品情報管理サービス
 * @author Nancy
 * @since 2025-03-28
 * @version 2.0
 */
@Service
public class AdminProductManagementService {
	@Autowired
	private AdminProductManagementRepository adminProductManagementRepository;

	/**
	 * 画面から登録された情報をEntityに変換して保存し、保存結果もDTOで返す
	 * @param adminProductInfo
	 * @return　商品情報結果
	 */
	public ProductInfoDto createProductInfo(ProductInfoDto adminProductInfo) {
		ProductInfoEntity adminProductManagementEntity = new ProductInfoEntity();

		adminProductManagementEntity.setProductId(adminProductInfo.getProductId());
		adminProductManagementEntity.setProductName(adminProductInfo.getProductName());
		adminProductManagementEntity.setProductDescription(adminProductInfo.getProductDescription());
		adminProductManagementEntity.setCategoryName(adminProductInfo.getCategoryName());
		adminProductManagementEntity.setPrice(adminProductInfo.getPrice());
		adminProductManagementEntity.setStockQuantity(adminProductInfo.getStockQuantity());
		adminProductManagementEntity.setStockStatus(adminProductInfo.getStockStatus());
		adminProductManagementEntity.setDelFlag(adminProductInfo.getDelFlag());
		adminProductManagementEntity.setProductPhoto(adminProductInfo.getProductPhoto());

		//画面の商品情報をEntityにマッピングする
		ProductInfoEntity savedProductInfo = adminProductManagementRepository
				.save(adminProductManagementEntity);
		//取得された情報が画面DTOにセットする
		ProductInfoDto productInfoResult = new ProductInfoDto();
		productInfoResult.setProductId(savedProductInfo.getProductId());
		productInfoResult.setProductName(savedProductInfo.getProductName());
		productInfoResult.setProductDescription(savedProductInfo.getProductDescription());
		productInfoResult.setCategoryName(savedProductInfo.getCategoryName());
		productInfoResult.setPrice(savedProductInfo.getPrice());
		productInfoResult.setStockQuantity(savedProductInfo.getStockQuantity());
		productInfoResult.setStockStatus(savedProductInfo.getStockStatus());
		productInfoResult.setDelFlag(savedProductInfo.getDelFlag());
		productInfoResult.setProductPhoto(savedProductInfo.getProductPhoto());
		productInfoResult.setCreatedAt(savedProductInfo.getCreatedAt());
		productInfoResult.setUpdatedAt(savedProductInfo.getUpdatedAt());

		return productInfoResult;
	}

	/**
	 * 全ての商品が取得して、画面の商品一覧に映す
	 * @return　商品一覧リスト
	 */
	public List<ProductInfoDto> findAllProductInfo() {
		List<ProductInfoEntity> allProductInfo = adminProductManagementRepository.findAll();

		List<ProductInfoDto> adminProductDtoList = new ArrayList<>();

		for (ProductInfoEntity adminProductManagementEntity : allProductInfo) {
			ProductInfoDto adminProductManagementDto = new ProductInfoDto();
			adminProductManagementDto.setProductId(adminProductManagementEntity.getProductId());
			adminProductManagementDto.setProductName(adminProductManagementEntity.getProductName());
			adminProductManagementDto.setProductDescription(adminProductManagementEntity.getProductDescription());
			adminProductManagementDto.setCategoryName(adminProductManagementEntity.getCategoryName());
			adminProductManagementDto.setPrice(adminProductManagementEntity.getPrice());
			adminProductManagementDto.setStockQuantity(adminProductManagementEntity.getStockQuantity());
			adminProductManagementDto.setStockStatus(adminProductManagementEntity.getStockStatus());
			adminProductManagementDto.setDelFlag(adminProductManagementEntity.getDelFlag());
			adminProductManagementDto.setProductPhoto(adminProductManagementEntity.getProductPhoto());
			adminProductManagementDto.setCreatedAt(adminProductManagementEntity.getCreatedAt());
			adminProductManagementDto.setUpdatedAt(adminProductManagementEntity.getUpdatedAt());
			adminProductDtoList.add(adminProductManagementDto);
		}
		// 商品一覧リストに返却する
		return adminProductDtoList;
	}

	/**
	 * 商品IDによって該当商品を削除する
	 * @param productId
	 */
	public void deleteProductById(Long productId) {
		//商品IDの存在を確認する
		if (!adminProductManagementRepository.existsById(productId)) {
			//商品ID存在しない場合，例外をスローする
			throw new RuntimeException("該当商品が見つかりません。ID: " + productId);
		}
		//存在する場合、該当商品を削除する
		adminProductManagementRepository.deleteById(productId);
	}

	/**
	 * 選択した商品情報を編集する
	 * @param productInfo
	 * @return 
	 */
	public ProductInfoDto updateProductInfo(ProductInfoDto productInfo) {

		//商品IDで既存のデータを選択する
		ProductInfoEntity existingProduct = adminProductManagementRepository
				.findById(productInfo.getProductId())
				.orElseThrow(() -> new RuntimeException("対象商品が見つかりません。ID: " + productInfo.getProductId()));

		//該当商品の情報をセットする
		existingProduct.setProductId(productInfo.getProductId());
		existingProduct.setProductName(productInfo.getProductName());
		existingProduct.setProductDescription(productInfo.getProductDescription());
		existingProduct.setCategoryName(productInfo.getCategoryName());
		existingProduct.setPrice(productInfo.getPrice());
		existingProduct.setStockQuantity(productInfo.getStockQuantity());
		existingProduct.setStockStatus(productInfo.getStockStatus());
		existingProduct.setDelFlag(productInfo.getDelFlag());
		existingProduct.setProductPhoto(productInfo.getProductPhoto());

		//商品情報を更新する
		ProductInfoEntity updatedProduct = adminProductManagementRepository
				.save(existingProduct);

		//商品情報DTOに変換して返す
		ProductInfoDto adminProductInfoById = new ProductInfoDto();
		adminProductInfoById.setProductId(updatedProduct.getProductId());
		adminProductInfoById.setProductName(updatedProduct.getProductName());
		adminProductInfoById.setProductDescription(updatedProduct.getProductDescription());
		adminProductInfoById.setCategoryName(updatedProduct.getCategoryName());
		adminProductInfoById.setPrice(updatedProduct.getPrice());
		adminProductInfoById.setStockQuantity(updatedProduct.getStockQuantity());
		adminProductInfoById.setStockStatus(updatedProduct.getStockStatus());
		adminProductInfoById.setDelFlag(updatedProduct.getDelFlag());
		adminProductInfoById.setProductPhoto(updatedProduct.getProductPhoto());
		adminProductInfoById.setCreatedAt(updatedProduct.getCreatedAt());
		adminProductInfoById.setUpdatedAt(updatedProduct.getUpdatedAt());
		
		return adminProductInfoById;
	}
}
